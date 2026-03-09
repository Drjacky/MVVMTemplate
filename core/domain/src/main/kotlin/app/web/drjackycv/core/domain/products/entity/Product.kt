package app.web.drjackycv.core.domain.products.entity

import androidx.compose.runtime.Immutable

@Immutable
data class Location(
    val name: String,
    val url: String,
)

@Immutable
data class Product(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)
