package app.web.drjackycv.data.products.entity


import app.web.drjackycv.domain.products.entity.Beer
import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    val info: Info,
    val results: List<BeerResponse>
) {
    data class Info(
        val count: Int,
        val pages: Int,
        val next: String?,
        val prev: String?,
    )
}

data class BeerResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("image") val image: String,
    @SerializedName("url") val url: String
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