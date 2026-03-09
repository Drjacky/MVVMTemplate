package app.web.drjackycv.feature.products.entity

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import app.web.drjackycv.core.domain.products.entity.Location
import app.web.drjackycv.core.domain.products.entity.Product
import kotlinx.parcelize.Parcelize

@Immutable
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

@Immutable
@Parcelize
data class ProductUI(
    val id: Int,
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
) : Parcelable

fun Product.mapIt(): ProductUI =
    ProductUI(
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
