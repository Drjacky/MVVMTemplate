package app.web.drjackycv.presentation.base.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_loading_row.view.*

class LoadingStateViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(loadState: LoadState) {
        view.itemLoadingRowContainer.isVisible = loadState is LoadState.Loading
    }

}