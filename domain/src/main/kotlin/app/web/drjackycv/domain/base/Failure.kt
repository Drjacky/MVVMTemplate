package app.web.drjackycv.domain.base

sealed class Failure(var msg: String?, var retryAction: () -> Unit) : Throwable() {

    class Timeout(msg: String?) : Failure(msg, {})

    class NoInternet(msg: String?) : Failure(msg, {})

    class Unknown(msg: String?) : Failure(msg, {})

}