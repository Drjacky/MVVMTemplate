package app.web.drjackycv.feature.products.entity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import app.web.drjackycv.feature.products.R
import app.web.drjackycv.feature.products.base.adapter.Cell
import app.web.drjackycv.feature.products.base.adapter.RecyclerItem
import app.web.drjackycv.feature.products.databinding.FeatureProductsItemProductBinding

object ProductCell : Cell<RecyclerItem, ViewBinding> {

    override fun belongsTo(item: RecyclerItem?): Boolean {
        return item is ProductUI
    }

    override fun type(): Int {
        return R.layout.feature_products_item_product
    }

    override fun binding(parent: ViewGroup): FeatureProductsItemProductBinding {
        return FeatureProductsItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ProductViewHolder(binding(parent))
    }

    override fun bind(
        holder: RecyclerView.ViewHolder,
        item: RecyclerItem?,
        onItemClick: ((RecyclerItem, View) -> Unit)?,
    ) {
        if (holder is ProductViewHolder && item is ProductUI) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onItemClick?.run {
                    this(item, holder.itemBinding.itemProductContainer)
                }
            }
        }
    }
}
