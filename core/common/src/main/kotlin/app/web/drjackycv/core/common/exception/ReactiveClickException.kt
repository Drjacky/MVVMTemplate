package app.web.drjackycv.core.common.exception

class ReactiveClickException(
    msg: String,
    cause: Throwable?,
    stack: Array<StackTraceElement>,
) : Throwable("$msg ::: ${stack.joinToString()}", cause, true, true)
