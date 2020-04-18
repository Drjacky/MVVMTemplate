package app.web.drjackycv.presentation.products.entity

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.base.adapter.Cell
import app.web.drjackycv.presentation.base.adapter.RecyclerItem

object ItemCell : Cell<RecyclerItem>() {

    override fun belongsTo(item: RecyclerItem?): Boolean {
        return item is ItemUI
    }

    override fun type(): Int {
        return R.layout.item_product
    }

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ItemViewHolder(parent.viewOf(type()))
    }

    override fun bind(
        holder: RecyclerView.ViewHolder,
        item: RecyclerItem?,
        onItemClick: ((RecyclerItem) -> Unit)?
    ) {
        if (holder is ItemViewHolder && item is ItemUI) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onItemClick?.run { this(item) }
            }
        }
    }

}