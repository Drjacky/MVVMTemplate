package app.web.drjackycv.core.network

data class ErrorBody(
    val statusCode: Int,
    val error: String,
    val message: String,
)