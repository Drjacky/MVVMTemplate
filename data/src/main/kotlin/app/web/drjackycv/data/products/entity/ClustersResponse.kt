package app.web.drjackycv.data.products.entity


import app.web.drjackycv.data.base.ResponseObject
import app.web.drjackycv.domain.products.entity.Cluster
import app.web.drjackycv.domain.products.entity.Clusters
import com.google.gson.annotations.SerializedName

data class ClustersResponse(
    @SerializedName("clusters") val clusters: List<Cluster>
) : ResponseObject<Clusters> {

    override fun toDomain(): Clusters = Clusters(
        clusters = clusters
    )

}