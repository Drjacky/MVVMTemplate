package app.web.drjackycv.presentation.base.mapper

interface MapperUI<DomainObject, UIObject> {

    fun mapToUI(obj: DomainObject): UIObject

}