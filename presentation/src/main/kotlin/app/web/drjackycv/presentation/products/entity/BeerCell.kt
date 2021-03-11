package app.web.drjackycv.presentation.products.entity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.base.adapter.Cell
import app.web.drjackycv.presentation.base.adapter.RecyclerItem
import app.web.drjackycv.presentation.databinding.ItemProductBinding

object BeerCell : Cell<RecyclerItem, ViewBinding> {

    override fun belongsTo(item: RecyclerItem?): Boolean {
        return item is BeerUI
    }

    override fun type(): Int {
        return R.layout.item_product
    }

    override fun binding(parent: ViewGroup): ItemProductBinding {
        return ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        return BeerViewHolder(binding(parent))
    }

    override fun bind(
        holder: RecyclerView.ViewHolder,
        item: RecyclerItem?,
        onItemClick: ((RecyclerItem, View) -> Unit)?
    ) {
        if (holder is BeerViewHolder && item is BeerUI) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onItemClick?.run {
                    this(item, holder.itemBinding.itemProductContainer)
                }
            }
        }
    }

}