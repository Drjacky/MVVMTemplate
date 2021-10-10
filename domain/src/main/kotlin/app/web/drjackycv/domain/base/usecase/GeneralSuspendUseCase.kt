package app.web.drjackycv.domain.base.usecase

interface GeneralSuspendUseCase<Type, in Params> {

    suspend fun execute(params: Params): Type

}