package app.web.drjackycv.feature.products.productlist

import android.view.View
import app.web.drjackycv.feature.products.base.adapter.BasePagedListAdapter
import app.web.drjackycv.feature.products.base.adapter.RecyclerItem
import app.web.drjackycv.feature.products.entity.BeerCell

class ProductsListAdapter(onItemClick: (RecyclerItem, View) -> Unit) : BasePagedListAdapter(
    BeerCell,
    onItemClick = onItemClick,
)
