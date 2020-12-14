package app.web.drjackycv.data.network

import android.content.Context
import app.web.drjackycv.data.BuildConfig.DEBUG
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val TIMEOUT = 5L

@Singleton
class BaseHttpClient @Inject constructor(
    @ApplicationContext appContext: Context,
    chuckerCollector: ChuckerCollector
) {

    private val chuckerInterceptor = ChuckerInterceptor.Builder(appContext)
        .collector(chuckerCollector)
        .maxContentLength(250000L)
        .redactHeaders(emptySet())
        .build()

    val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level =
                    if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }
        )
        .addInterceptor(chuckerInterceptor)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()

}