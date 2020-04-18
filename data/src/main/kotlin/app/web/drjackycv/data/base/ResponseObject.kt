package app.web.drjackycv.data.base

interface ResponseObject<out DomainObject : Any?> {

    fun toDomain(): DomainObject

}