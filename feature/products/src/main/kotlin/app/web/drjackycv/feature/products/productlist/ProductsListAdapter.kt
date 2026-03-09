package app.web.drjackycv.feature.products.productlist

import android.view.View
import app.web.drjackycv.feature.products.base.adapter.BasePagedListAdapter
import app.web.drjackycv.feature.products.base.adapter.RecyclerItem
import app.web.drjackycv.feature.products.entity.ProductCell

class ProductsListAdapter(onItemClick: (RecyclerItem, View) -> Unit) : BasePagedListAdapter(
    ProductCell,
    onItemClick = onItemClick,
)
