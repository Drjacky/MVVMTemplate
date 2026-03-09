package app.web.drjackycv.core.network

import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseRetrofit @Inject constructor(
    okHttpClient: OkHttpClient,
    json: Json,
) {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addCallAdapterFactory(RxJavaCustomCallAdapterFactory.create())
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(okHttpClient)
        .build()
}
