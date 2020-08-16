package app.web.drjackycv.presentation.products.entity

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.base.adapter.Cell
import app.web.drjackycv.presentation.products.productlist.BeerMapper
import kotlinx.android.synthetic.main.item_product.view.*

object BeerCell : Cell<RecyclerItem>() {

    override fun belongsTo(item: RecyclerItem?): Boolean {
        return item is Beer
    }

    override fun type(): Int {
        return R.layout.item_product
    }

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        return BeerViewHolder(parent.viewOf(type()))
    }

    override fun bind(
        holder: RecyclerView.ViewHolder,
        item: RecyclerItem?,
        onItemClick: ((RecyclerItem, ImageView) -> Unit)?
    ) {
        if (holder is BeerViewHolder && item is Beer) {
            val itemUI = BeerMapper().mapToUI(item)

            holder.bind(itemUI)
            holder.itemView.setOnClickListener {
                onItemClick?.run { this(item, holder.itemView.itemProductImv) }
            }
        }
    }

}