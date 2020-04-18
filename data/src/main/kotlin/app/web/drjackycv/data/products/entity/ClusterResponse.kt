package app.web.drjackycv.data.products.entity


import app.web.drjackycv.data.base.ResponseObject
import app.web.drjackycv.domain.products.entity.Cluster
import app.web.drjackycv.domain.products.entity.Item
import com.google.gson.annotations.SerializedName

data class ClusterResponse(
    @SerializedName("tag") val tag: String,
    @SerializedName("items") val items: List<Item>
) : ResponseObject<Cluster> {

    override fun toDomain(): Cluster = Cluster(
        tag = tag,
        items = items
    )

}