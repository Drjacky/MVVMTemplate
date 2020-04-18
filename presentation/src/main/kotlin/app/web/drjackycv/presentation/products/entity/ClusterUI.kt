package app.web.drjackycv.presentation.products.entity

import app.web.drjackycv.presentation.base.adapter.RecyclerItem

data class ClusterUI(
    override val id: Int,
    val tag: String,
    val items: List<ItemUI>
) : RecyclerItem