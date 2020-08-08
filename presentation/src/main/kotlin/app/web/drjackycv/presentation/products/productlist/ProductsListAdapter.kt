package app.web.drjackycv.presentation.products.productlist

import android.widget.ImageView
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.presentation.base.adapter.BasePagedListAdapter
import app.web.drjackycv.presentation.products.entity.BeerCell

class ProductsListAdapter(onItemClick: (RecyclerItem, ImageView) -> Unit) : BasePagedListAdapter(
    BeerCell,
    onItemClick = onItemClick
)