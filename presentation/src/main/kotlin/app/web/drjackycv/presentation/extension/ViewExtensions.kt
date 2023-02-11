package app.web.drjackycv.presentation.extension

import android.app.Activity
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat.startPostponedEnterTransition
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.base.util.GlideApp
import app.web.drjackycv.presentation.exception.ReactiveClickException
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

fun View?.gone() {
    this?.let {
        visibility = View.GONE
    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun ImageView.load(
    url: String?,
    @DrawableRes placeholderRes: Int = R.drawable.ic_cloud_download,
    activity: Activity? = null,
) {
    val safePlaceholderDrawable = AppCompatResources.getDrawable(context, placeholderRes)
    val requestOptions = RequestOptions().apply {
        placeholder(safePlaceholderDrawable)
        error(safePlaceholderDrawable)
    }
    val glideRequest = GlideApp
        .with(context)
        .setDefaultRequestOptions(requestOptions)
        .load(url)
        .dontAnimate()

    activity?.let {
        glideRequest
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean,
                ): Boolean {
                    startPostponedEnterTransition(it)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean,
                ): Boolean {
                    startPostponedEnterTransition(it)
                    return false
                }
            })
    }

    glideRequest.into(this)
}

fun View.setOnReactiveClickListener(windowDuration: Long = 500, action: (() -> Unit)?): Disposable =
    this.clicks()
        .throttleFirst(windowDuration, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            action?.invoke()
        }, { throwable ->
            throw ReactiveClickException(
                msg = throwable.message ?: "Unknown Reactive Click Exception!",
                cause = throwable.cause,
                stack = throwable.stackTrace
            )
        })

fun AppCompatActivity.setStatusBarColor(@ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        with(window) {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            WindowInsetsControllerCompat(window, decorView).isAppearanceLightStatusBars =
                this@setStatusBarColor.isDarkMode().not()
            ContextCompat.getColor(this@setStatusBarColor, color).let {
                window?.statusBarColor = it
            }
        }
    }
}

fun AppCompatActivity.isDarkMode(): Boolean {
    return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> true
        Configuration.UI_MODE_NIGHT_NO -> false
        Configuration.UI_MODE_NIGHT_UNDEFINED -> true
        else -> false
    }
}
