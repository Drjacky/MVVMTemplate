package app.web.drjackycv.presentation.products.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeerUI(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String?,
    val abv: Double
) : Parcelable