package br.com.sudosu.desafio

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDexApplication
import br.com.sudosu.desafio.services.RetrofitServices
import com.instacart.library.truetime.TrueTime
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DesafioApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        context = this
        appInstance = this

        RetrofitServices.initialize(this)
        GlobalScope.launch {
            try {
                TrueTime.build().initialize()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null
        private var appInstance: DesafioApplication? = null

        fun getApplicationContext() = context

        fun getInstance(): DesafioApplication {
            checkNotNull(appInstance) { "Configure a classe de Application no AndroidManifest.xml" }
            return appInstance!!
        }
    }
}