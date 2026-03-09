package app.web.drjackycv.feature.products.base.adapter

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import app.web.drjackycv.feature.products.databinding.FeatureProductsItemLoadingRowBinding

class LoadingStateViewHolder(private val itemBinding: FeatureProductsItemLoadingRowBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(loadState: LoadState) {
        itemBinding.itemLoadingRowContainer.isVisible = loadState is LoadState.Loading
    }
}
