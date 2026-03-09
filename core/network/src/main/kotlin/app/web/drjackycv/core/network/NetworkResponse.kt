package app.web.drjackycv.core.network

import java.io.IOException

sealed class NetworkResponse<out T : Any, out U : Any> {

    data class Success<T : Any>(val body: T) : NetworkResponse<T, Nothing>()

    data class ApiError<U : Any>(val body: U?, val code: Int?) :
        NetworkResponse<Nothing, U>()

    data class NetworkError(val error: IOException) :
        NetworkResponse<Nothing, Nothing>()
}
