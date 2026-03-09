package app.web.drjackycv.feature.products.entity

import android.os.Parcelable
import app.web.drjackycv.core.domain.products.entity.Beer
import app.web.drjackycv.core.domain.products.entity.Location
import app.web.drjackycv.feature.products.base.adapter.RecyclerItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationUI(
    val name: String,
    val url: String,
) : Parcelable

fun Location.mapIt(): LocationUI =
    LocationUI(
        name = name,
        url = url,
    )

@Parcelize
data class BeerUI(
    override val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationUI,
    val location: LocationUI,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
) : RecyclerItem, Parcelable

fun Beer.mapIt(): BeerUI =
    BeerUI(
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
