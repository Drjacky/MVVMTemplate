package app.web.drjackycv.presentation.products.productlist

import app.web.drjackycv.domain.products.entity.Clusters
import app.web.drjackycv.presentation.base.mapper.MapperUI
import app.web.drjackycv.presentation.products.entity.ClustersUI

class ClustersMapper : MapperUI<Clusters, ClustersUI> {

    override fun mapToUI(obj: Clusters): ClustersUI = with(obj) {
        ClustersUI(
            clusters = clusters.map { ClusterMapper().mapToUI(it) }
        )
    }

}