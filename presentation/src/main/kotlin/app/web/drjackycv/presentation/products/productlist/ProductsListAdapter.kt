package app.web.drjackycv.presentation.products.productlist

import android.view.View
import app.web.drjackycv.presentation.base.adapter.BasePagedListAdapter
import app.web.drjackycv.presentation.base.adapter.RecyclerItem
import app.web.drjackycv.presentation.products.entity.BeerCell

class ProductsListAdapter(onItemClick: (RecyclerItem, View) -> Unit) : BasePagedListAdapter(
    BeerCell,
    onItemClick = onItemClick
)