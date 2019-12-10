package br.com.sudosu.desafio.services.models

sealed class RequestError(val code: Int?){
    class HttpError(code: Int?): RequestError(code)
    class DesafioError(
        code: Int?,
        val message: String? = null,
        val details: String? = null,
        val id: String? = null
    ) : RequestError(code)
}

data class ServiceResponse<T>(
    val data: T? = null,
    val error: RequestError? = null
){
    companion object{
        fun <T> httpError(code: Int?): ServiceResponse<T> = ServiceResponse(error = RequestError.HttpError(code))

        fun <T> desafioError(
            code: Int?,
            message: String? = null,
            details: String? = null,
            userId: String? = null
        ): ServiceResponse<T> = ServiceResponse(error = RequestError.DesafioError(code, message, details, userId))

        fun <T> success(data: T?): ServiceResponse<T> = ServiceResponse(data = data)
    }
}