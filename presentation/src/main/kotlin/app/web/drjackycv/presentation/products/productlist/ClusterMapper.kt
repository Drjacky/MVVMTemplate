package app.web.drjackycv.presentation.products.productlist

import app.web.drjackycv.domain.products.entity.Cluster
import app.web.drjackycv.presentation.base.mapper.MapperUI
import app.web.drjackycv.presentation.products.entity.ClusterUI

class ClusterMapper : MapperUI<Cluster, ClusterUI> {

    override fun mapToUI(obj: Cluster): ClusterUI = with(obj) {
        ClusterUI(
            id = tag.hashCode(),
            tag = tag,
            items = items.map { ItemMapper().mapToUI(it) }
        )
    }

}