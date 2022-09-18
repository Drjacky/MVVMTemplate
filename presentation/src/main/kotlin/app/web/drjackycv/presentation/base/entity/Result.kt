package app.web.drjackycv.presentation.base.entity

import app.web.drjackycv.domain.base.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val failure: Throwable) : Result<Nothing>
    object Loading : Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> {
            Result.Success(it)
        }
        .onStart { emit(Result.Loading) }
        .catch { e ->
            when (e) {
                is UnknownHostException, is SocketTimeoutException -> {
                    emit(Result.Error(Failure.NoInternet(e.message)))
                }
                is TimeoutException -> {
                    emit(Result.Error(Failure.Timeout(e.message)))
                }
                else -> {
                    emit(Result.Error(Failure.Unknown(e.message)))
                }
            }
        }
}