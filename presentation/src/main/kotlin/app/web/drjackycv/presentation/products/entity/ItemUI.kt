package app.web.drjackycv.presentation.products.entity

import app.web.drjackycv.presentation.base.adapter.RecyclerItem

data class ItemUI(
    override val id: Int,
    val price: String,
    val title: String,
    val size: String,
    val imageUrl: String
) : RecyclerItem