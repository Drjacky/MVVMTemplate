package app.web.drjackycv.domain.products.entity

import app.web.drjackycv.domain.base.RecyclerItem

data class Beer(
    override val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String?,
    val abv: Double
) : RecyclerItem