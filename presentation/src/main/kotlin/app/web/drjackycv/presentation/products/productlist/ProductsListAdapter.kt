package app.web.drjackycv.presentation.products.productlist

import app.web.drjackycv.presentation.base.adapter.BaseListAdapter
import app.web.drjackycv.presentation.base.adapter.RecyclerItem
import app.web.drjackycv.presentation.products.entity.ClusterCell
import app.web.drjackycv.presentation.products.entity.ItemCell

class ProductsListAdapter(onItemClick: (RecyclerItem) -> Unit) : BaseListAdapter(
    ItemCell, ClusterCell,
    onItemClick = onItemClick
)