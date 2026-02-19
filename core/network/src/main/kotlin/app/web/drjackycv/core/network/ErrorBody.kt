package app.web.drjackycv.core.network

import kotlinx.serialization.Serializable

@Serializable
data class ErrorBody(
    val statusCode: Int,
    val error: String,
    val message: String,
)