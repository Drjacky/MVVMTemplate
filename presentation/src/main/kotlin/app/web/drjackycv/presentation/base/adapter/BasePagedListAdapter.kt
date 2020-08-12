package app.web.drjackycv.presentation.base.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import app.web.drjackycv.domain.base.RecyclerItem

abstract class BasePagedListAdapter(
    vararg types: Cell<RecyclerItem>,
    private val onItemClick: (RecyclerItem, ImageView) -> Unit
) : PagingDataAdapter<RecyclerItem, RecyclerView.ViewHolder>(BASE_DIFF_CALLBACK) {

    private val cellTypes: CellTypes<RecyclerItem> = CellTypes(*types)

    override fun getItemViewType(position: Int): Int {
        getItem(position).let {
            return cellTypes.of(it).type()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return cellTypes.of(viewType).holder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position).let {
            cellTypes.of(it).bind(holder, it, onItemClick)
        }
    }

}