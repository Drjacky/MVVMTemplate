package app.web.drjackycv.feature.products.base.adapter

interface RecyclerItem {
    val id: Int?
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}
