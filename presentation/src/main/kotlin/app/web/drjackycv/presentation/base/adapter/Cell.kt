package app.web.drjackycv.presentation.base.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.web.drjackycv.domain.base.RecyclerItem

abstract class Cell<T, R> {

    abstract fun belongsTo(item: T?): Boolean
    abstract fun type(): Int
    abstract fun binding(parent: ViewGroup): R
    abstract fun holder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun bind(
        holder: RecyclerView.ViewHolder,
        item: T?,
        onItemClick: ((RecyclerItem, View) -> Unit)?
    )

}