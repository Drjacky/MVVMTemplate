package app.web.drjackycv.feature.products.entity

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import app.web.drjackycv.core.domain.products.entity.Beer
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class BeerUI(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val url: String,
) : Parcelable

fun Beer.mapIt(): BeerUI =
    BeerUI(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image,
        url = url,
    )