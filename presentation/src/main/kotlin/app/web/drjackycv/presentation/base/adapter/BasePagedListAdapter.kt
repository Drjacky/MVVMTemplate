package app.web.drjackycv.presentation.base.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BasePagedListAdapter(
    vararg types: Cell<RecyclerItem, ViewBinding>,
    private val onItemClick: (RecyclerItem, View) -> Unit
) : PagingDataAdapter<RecyclerItem, RecyclerView.ViewHolder>(BASE_DIFF_CALLBACK) {

    @Suppress("SpreadOperator")
    private val cellTypes: CellTypes<RecyclerItem, ViewBinding> = CellTypes(*types)

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