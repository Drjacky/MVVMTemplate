package app.web.drjackycv.presentation.base.adapter

class CellTypes<T, R>(vararg types: Cell<T, R>) {

    private val cellTypesList: ArrayList<Cell<T, R>> = ArrayList()

    init {
        types.forEach { addType(it) }
    }

    private fun addType(type: Cell<T, R>) {
        cellTypesList.add(type)
    }

    fun of(item: T?): Cell<T, R> {
        for (cellType in cellTypesList) {
            if (cellType.belongsTo(item)) return cellType
        }
        throw NoSuchRecyclerItemTypeException()
    }

    fun of(viewType: Int): Cell<T, R> {
        for (cellType in cellTypesList) {
            if (cellType.type() == viewType) return cellType
        }
        throw NoSuchRecyclerViewTypeException()
    }

}