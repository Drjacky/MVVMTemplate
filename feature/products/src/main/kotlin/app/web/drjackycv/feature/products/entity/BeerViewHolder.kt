package app.web.drjackycv.feature.products.entity

import androidx.recyclerview.widget.RecyclerView
import app.web.drjackycv.feature.products.databinding.ItemProductBinding
import app.web.drjackycv.feature.products.extension.load

class BeerViewHolder(val itemBinding: ItemProductBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(beer: BeerUI) = with(itemView) {
        itemBinding.itemProductContainer.transitionName = beer.id.toString()
        itemBinding.itemProductIdTxv.text = beer.id.toString()
        itemBinding.itemProductImv.load(beer.image)
        itemBinding.itemProductNameTxv.text = beer.name
        itemBinding.itemProductStatusTxv.text = beer.status
    }
}
