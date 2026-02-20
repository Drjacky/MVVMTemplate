package app.web.drjackycv.core.data.products.entity

import app.web.drjackycv.core.domain.products.entity.Beer
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val info: Info,
    val results: List<BeerResponse>,
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
data class BeerResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val url: String,
)

fun BeerResponse.mapIt(): Beer =

    Beer(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image,
        url = url,
    )
