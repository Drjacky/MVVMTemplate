package app.web.drjackycv.presentation.base.adapter

class CellTypes<T>(vararg types: Cell<T>) {

    private val cellTypes: ArrayList<Cell<T>> = ArrayList()

    init {
        types.forEach { addType(it) }
    }

    private fun addType(type: Cell<T>) {
        cellTypes.add(type)
    }

    fun of(item: T?): Cell<T> {
        for (cellType in cellTypes) {
            if (cellType.belongsTo(item)) return cellType
        }
        throw NoSuchRecyclerItemTypeException()
    }

    fun of(viewType: Int): Cell<T> {
        for (cellType in cellTypes) {
            if (cellType.type() == viewType) return cellType
        }
        throw NoSuchRecyclerViewTypeException()
    }

}