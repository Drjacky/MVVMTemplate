package app.web.drjackycv.presentation.products.entity

import android.os.Parcelable
import app.web.drjackycv.domain.products.entity.Beer
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeerUI(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String?,
    val abv: Double,
) : Parcelable

fun Beer.mapIt(): BeerUI =
    BeerUI(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        imageUrl = imageUrl,
        abv = abv
    )