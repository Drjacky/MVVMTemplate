package app.web.drjackycv.presentation.products.entity

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.base.adapter.Cell
import app.web.drjackycv.presentation.base.adapter.RecyclerItem

object ClusterCell : Cell<RecyclerItem>() {

    override fun belongsTo(item: RecyclerItem?): Boolean {
        return item is ClusterUI
    }

    override fun type(): Int {
        return R.layout.item_cluster
    }

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ClusterViewHolder(parent.viewOf(type()))
    }

    override fun bind(
        holder: RecyclerView.ViewHolder,
        item: RecyclerItem?,
        onItemClick: ((RecyclerItem) -> Unit)?
    ) {
        if (holder is ClusterViewHolder && item is ClusterUI) {
            holder.bind(item)
        }
    }

}