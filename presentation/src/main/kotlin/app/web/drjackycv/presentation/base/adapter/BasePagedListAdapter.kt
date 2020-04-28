package app.web.drjackycv.presentation.base.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.web.drjackycv.domain.base.RecyclerItem

abstract class BasePagedListAdapter(
    vararg types: Cell<RecyclerItem>,
    private val onItemClick: (RecyclerItem) -> Unit
) : PagedListAdapter<RecyclerItem, RecyclerView.ViewHolder>(BASE_DIFF_CALLBACK) {

    private val cellTypes: CellTypes<RecyclerItem> = CellTypes(*types)

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return cellTypes.of(item).type()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return cellTypes.of(viewType).holder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        cellTypes.of(item).bind(holder, item, onItemClick)
    }

}