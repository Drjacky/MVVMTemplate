package app.web.drjackycv.core.domain.base

sealed class Failure(
    val msg: String?,
    val retryAction: () -> Unit = {},
) : Throwable() {

    class Api(msg: String? = null, retryAction: () -> Unit = {}) : Failure(msg, retryAction)

    class Timeout(msg: String? = null, retryAction: () -> Unit = {}) : Failure(msg, retryAction)

    class NoInternet(msg: String? = null, retryAction: () -> Unit = {}) : Failure(msg, retryAction)

    class Unknown(msg: String? = null, retryAction: () -> Unit = {}) : Failure(msg, retryAction)
}
