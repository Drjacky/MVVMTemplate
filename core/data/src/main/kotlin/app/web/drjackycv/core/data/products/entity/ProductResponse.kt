package app.web.drjackycv.core.data.products.entity

import app.web.drjackycv.core.domain.products.entity.Location
import app.web.drjackycv.core.domain.products.entity.Product
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val info: Info,
    val results: List<ProductResponse>,
) {
    @Serializable
    data class Info(
        val count: Int,
        val pages: Int,
        val next: String?,
        val prev: String?,
    )
}

@Serializable
data class LocationResponse(
    val name: String,
    val url: String,
)

fun LocationResponse.mapIt(): Location =
    Location(
        name = name,
        url = url,
    )

@Serializable
data class ProductResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationResponse,
    val location: LocationResponse,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)

fun ProductResponse.mapIt(): Product =
    Product(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.mapIt(),
        location = location.mapIt(),
        image = image,
        episode = episode,
        url = url,
        created = created,
    )
