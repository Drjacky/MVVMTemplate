package app.web.drjackycv.core.network

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class RxJavaCustomCallAdapterFactory private constructor() : CallAdapter.Factory() {

    companion object {
        @JvmStatic
        fun create() = RxJavaCustomCallAdapterFactory()
    }

    @Suppress("ComplexCondition", "ReturnCount")
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *>? {
        val rawType = getRawType(returnType)

        val isFlowable = rawType === Flowable::class.java
        val isSingle = rawType === Single::class.java
        val isMaybe = rawType === Maybe::class.java
        if (rawType !== Observable::class.java && !isFlowable && !isSingle && !isMaybe) {
            return null
        }

        if (returnType !is ParameterizedType) {
            throw IllegalStateException(
                "${rawType.simpleName} return type must be parameterized as " +
                    "${rawType.simpleName}<Foo> or ${rawType.simpleName}<? extends Foo>"
            )
        }

        val observableEmissionType = getParameterUpperBound(0, returnType)
        if (getRawType(observableEmissionType) != NetworkResponse::class.java) {
            return null
        }

        if (observableEmissionType !is ParameterizedType) {
            throw IllegalStateException(
                "NetworkResponse must be parameterized as NetworkResponse<SuccessBody, ErrorBody>"
            )
        }

        val successBodyType = getParameterUpperBound(0, observableEmissionType)
        val delegateType = parameterizedType(Observable::class.java, successBodyType)
        val delegateAdapter = retrofit.nextCallAdapter(
            this,
            delegateType,
            annotations
        )

        val errorBodyType = getParameterUpperBound(1, observableEmissionType)
        val errorBodyConverter = retrofit.nextResponseBodyConverter<Any>(
            null,
            errorBodyType,
            annotations
        )

        @Suppress("UNCHECKED_CAST")
        return RxJavaCustomCallAdapter(
            successBodyType,
            delegateAdapter as CallAdapter<Any, Observable<Any>>,
            errorBodyConverter,
            isFlowable,
            isSingle,
            isMaybe,
        )
    }

    private fun parameterizedType(rawType: Type, vararg typeArguments: Type): Type =
        object : ParameterizedType {
            override fun getRawType(): Type = rawType
            override fun getActualTypeArguments(): Array<out Type> = typeArguments
            override fun getOwnerType(): Type? = null
        }
}
