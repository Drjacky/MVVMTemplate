package app.web.drjackycv.domain.base.usecase

import io.reactivex.Single

interface SingleUseCase<Type, in Params> {

    operator fun invoke(params: Params): Single<Type>

}