package app.web.drjackycv.feature.products.entity

import androidx.recyclerview.widget.RecyclerView
import app.web.drjackycv.core.common.extension.load
import app.web.drjackycv.feature.products.databinding.FeatureProductsItemProductBinding

class ProductViewHolder(val itemBinding: FeatureProductsItemProductBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(product: ProductUI) = with(itemView) {
        itemBinding.itemProductContainer.transitionName = product.id.toString()
        itemBinding.itemProductIdTxv.text = product.id.toString()
        itemBinding.itemProductImv.load(product.image, cardView = itemBinding.itemProductContainer)
        itemBinding.itemProductNameTxv.text = product.name
        itemBinding.itemProductStatusTxv.text = product.status
    }
}
