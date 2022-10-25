package app.web.drjackycv.presentation.base.entity

import app.web.drjackycv.domain.base.Failure
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

sealed interface Result<out T : Any> {
    data class Success<T : Any>(val data: T) : Result<T>
    data class Error(val failure: Throwable) : Result<Nothing>
    object Loading : Result<Nothing>
}

fun <T : Any> Flow<T>.asResult(): Flow<Result<T>> {
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

fun <T : Any> Flowable<T>.asResult(): Flowable<Result<T>> =
    this
        .map<Result<T>> {
            Result.Success(it)
        }
        //.doOnSubscribe { Flowable.just(Result.Loading) }
        .startWith(Flowable.just(Result.Loading))
        .onErrorReturn { e ->
            when (e) {
                is UnknownHostException, is SocketTimeoutException -> {
                    Result.Error(Failure.NoInternet(e.message))
                }
                is TimeoutException -> {
                    Result.Error(Failure.Timeout(e.message))
                }
                else -> {
                    Result.Error(Failure.Unknown(e.message))
                }
            }
        }