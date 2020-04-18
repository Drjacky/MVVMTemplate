package app.web.drjackycv.presentation.products.entity

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_cluster.view.*

class ClusterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ClusterUI) = with(itemView) {
        itemClusterTitleTxv.text = item.tag
    }

}