package app.web.drjackycv.core.domain.products.entity

import androidx.compose.runtime.Immutable

@Immutable
data class Beer(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val url: String,
)