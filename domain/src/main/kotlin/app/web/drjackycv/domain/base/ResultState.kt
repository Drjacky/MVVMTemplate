package app.web.drjackycv.domain.base


sealed class ResultState<T> {

    data class Success<T>(val data: T) : ResultState<T>()

    data class Error<T>(val failure: Failure, val data: T?) : ResultState<T>()

}