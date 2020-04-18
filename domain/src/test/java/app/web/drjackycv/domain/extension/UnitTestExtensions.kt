package app.web.drjackycv.domain.extension

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

fun <T> getSingleSuccess(value: T): Single<T> =
    Single.just(value)

fun <T : List<Any>> getSingleListSuccess(value: T): Single<T> =
    Single.just(value)

fun <T> getSingleError(value: Throwable = getFailureError()): Single<T> = Single.error(value)

fun <T : Any> Single<T>.testAwait(): TestObserver<T> = this.test().apply {
    this.awaitTerminalEvent()
}

private fun getFailureError() = Failure.FailureWithMessage("Error")

