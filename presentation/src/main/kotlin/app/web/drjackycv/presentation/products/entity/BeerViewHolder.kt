package app.web.drjackycv.presentation.products.entity

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.web.drjackycv.presentation.extension.load
import kotlinx.android.synthetic.main.item_product.view.*

class BeerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(beer: BeerUI) = with(itemView) {
        itemProductImv.transitionName = beer.id.toString()
        itemProductIdTxv.text = beer.id.toString()
        itemProductImv.load(beer.imageUrl)
        itemProductNameTxv.text = beer.name
        itemProductAbvTxv.text = beer.abv.toString()
        //itemProductTypeTxv.text = beer.type
    }

}