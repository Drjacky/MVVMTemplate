package app.web.drjackycv.data.network

import app.web.drjackycv.data.base.ResponseObject
import retrofit2.Call

open class BaseRemoteDataSource {

    fun <RO : List<ResponseObject<DO>>, DO : Any> syncRequest(
        request: Call<RO>,
        onSuccess: (List<DO>?) -> Unit
    ) {
        val response = request.execute()
        onSuccess(response.body()?.map { it.toDomain() })
    }

}