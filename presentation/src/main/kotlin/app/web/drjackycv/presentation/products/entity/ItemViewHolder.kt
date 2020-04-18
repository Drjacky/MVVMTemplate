package app.web.drjackycv.presentation.products.entity

import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import app.web.drjackycv.presentation.extension.load
import kotlinx.android.synthetic.main.item_product.view.*

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ItemUI) = with(itemView) {
        ViewCompat.setTransitionName(itemProductProductImv, item.id.toString())
        itemProductProductImv.load(item.imageUrl)
        itemProductTitleTxv.text = item.title
        itemProductPrice.text = item.price
        itemProductSize.text = item.size
        //setOnClickListener { onItemClick(item.id) }
    }

}