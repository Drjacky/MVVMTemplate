package app.web.drjackycv.core.domain.base.mapper

interface Mapper<in LeftObject, out RightObject> {
    fun mapLeftToRight(obj: LeftObject): RightObject
}
