package app.web.drjackycv.domain.base

interface RecyclerItem {
    val id: Int?
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}