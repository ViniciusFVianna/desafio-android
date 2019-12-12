package br.com.sudosu.desafio.services

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.sudosu.desafio.BuildConfig
import br.com.sudosu.desafio.services.models.ServiceResponse
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit

class RetrofitServices private constructor(private val context: Context){
    @PublishedApi
    internal lateinit var retrofit: Retrofit

    init {
        initRetrofit()
    }

    private fun initRetrofit() {

        Log.d("RETROFIT", "Retrofit initialized!")

        //Configura o cliente Http
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val gsonConfig = GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        //Cria a instancia do Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(getBaseURL())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gsonConfig))
            .build()
    }

    /**
     * Cria e retorna uma instancia de um servico do tipo T
     */
    inline fun <reified T> getService(): T {
        return retrofit.create(T::class.java)
    }

    companion object {
        private val BASE_URL_PROD = "http://dev.4all.com:3003"
        private val BASE_URL_DEV = "http://dev.4all.com:3003"

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: RetrofitServices? = null

        fun getInstance(): RetrofitServices {
            try {
                return INSTANCE!!
            } catch (e: NullPointerException) {
                throw NullPointerException("Retrofit Services was not initialized. You must call RetrofitServices.initialize() on your application before using.")
            }
        }

        fun initialize(applicationContext: Context) {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = RetrofitServices(applicationContext)
                }
            }
        }

        fun getBaseURL() = if (BuildConfig.DEBUG) BASE_URL_DEV else BASE_URL_PROD
    }
}

fun <T> Call<T>.executeCallFunction(): LiveData<ServiceResponse<T>>{
    val liveData = MutableLiveData<ServiceResponse<T>>()

    enqueue(object : Callback<T> {

        val Max_RETRIES = 2
        var retryCount = 0

        override fun onResponse(call: Call<T>, response: Response<T>?) {

            try{
                response?.errorBody()?.string()?.also { errorBody ->
                    try {
                        // Prioriza o erro do servidor
                        val error = JSONObject(errorBody)
                        val code = error.getInt("err")

                        val msg = if (error.has("msg")){
                            error.getString("msg")
                        }else{
                            null
                        }

                        val detalhes = if (error.has("detlhes")){
                            error.getString("detalhes")
                        }else{
                            null
                        }
                        val userId = if(error.has("id")){
                            error.getString("id")
                        }else{
                            null
                        }

                        liveData.postValue(ServiceResponse.desafioError(code, msg, detalhes, userId))
                    }catch (e: Exception){
                        // Se nao conseguir repassar o erro do servidor, trata como erro normal.
                        liveData.postValue(ServiceResponse.httpError(response.code()))
                    }
                    return
                }
                response?.body()?.also {
                    liveData.postValue(ServiceResponse.success(data = it))
                    return
                }
                liveData.postValue(ServiceResponse())
            } catch (exception: JsonParseException){
                liveData.postValue(ServiceResponse.desafioError(null, "Erro ao desserializar entrada."))
            }
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            t?.printStackTrace()
            Log.w("REST/FALHA", "Falha na requisição: " + t?.localizedMessage)

            // Se a call foi cancelada, noa de retry
            if (call?.isCanceled == false){
                if (retryCount < Max_RETRIES){
                    Handler().postDelayed({ call.clone().enqueue(this) }, 1000 * 2)
                }else{
                    liveData.postValue(ServiceResponse.desafioError(null, t?.localizedMessage ?: ""))
                }
            }
        }
    })
    return liveData
}