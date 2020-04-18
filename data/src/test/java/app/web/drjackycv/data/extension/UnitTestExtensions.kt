package app.web.drjackycv.data.extension

import app.web.drjackycv.domain.base.usecase.Failure
import io.reactivex.Single
import io.reactivex.observers.TestObserver

fun <T : Any> TestObserver<T>.assertGeneralsSuccess(asserts: (T) -> Boolean = { true }) {
    assertComplete()
    assertValueCount(1)
    assertNoErrors()
    assertValue {
        asserts(it)
    }
}

fun <T : Any> TestObserver<T>.assertGeneralsError(asserts: (Throwable) -> Boolean = { true }) {
    assertValueCount(0)
    assertError {
        asserts(it)
    }
}

fun <T> getSingleResultSuccess(value: T): Single<T> =
    Single.just(value)

fun <T : List<Any>> getSingleListResultSuccess(value: T): Single<T> =
    Single.just(value)

fun <T> getSingleError(value: Throwable = getFailureError()): Single<T> = Single.error(value)

private fun getFailureError() = Failure.FailureWithMessage("Error")

