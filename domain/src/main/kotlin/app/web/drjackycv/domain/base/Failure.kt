package app.web.drjackycv.domain.base

sealed class Failure(var retryAction: () -> Unit) : Throwable() {

    class FailureWithMessage(val msg: String?) : Failure({})

}