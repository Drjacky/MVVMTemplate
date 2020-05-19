package app.web.drjackycv.data.network

import app.web.drjackycv.data.base.ResponseObject
import app.web.drjackycv.domain.base.Failure
import app.web.drjackycv.domain.base.ResultState
import retrofit2.Call

open class BaseRemoteDataSource {

    fun <RO : List<ResponseObject<DO>>, DO : Any> syncRequest(
        request: Call<RO>,
        onResult: (ResultState<List<DO>?>) -> Unit
    ) {
        try {
            val response = request.execute()
            val data = response.body()?.map { it.toDomain() } ?: emptyList()

            onResult(ResultState.Success(data))
        } catch (e: Exception) {
            onResult(
                ResultState.Error(
                    Failure.FailureWithMessage(e.message ?: "Unknown Error"), null
                )
            )
        }
    }

}