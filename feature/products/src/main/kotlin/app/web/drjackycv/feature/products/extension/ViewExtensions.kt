package app.web.drjackycv.feature.products.extension

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ImageView.load(
    url: String?,
    placeholder: Int? = null,
    activity: Activity? = null,
) {
    val safePlaceholderDrawable = if (placeholder != null) {
        ContextCompat.getDrawable(context, placeholder)
    } else {
        null
    }

    val requestOptions = RequestOptions().apply {
        placeholder(safePlaceholderDrawable)
        error(safePlaceholderDrawable)
        dontAnimate()
    }

    val glideRequest = Glide
        .with(context)
        .setDefaultRequestOptions(requestOptions)
        .load(url)

    if (activity != null) {
        glideRequest.into(object : CustomTarget<Drawable>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?,
            ) {
                setImageDrawable(resource)
                val bitmap = (resource as? BitmapDrawable)?.bitmap
                if (bitmap != null && !bitmap.isRecycled) {
                    handlePalette(bitmap, activity)
                }
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                setImageDrawable(placeholder)
            }
        })
    } else {
        glideRequest.into(this)
    }
}

private fun ImageView.handlePalette(bitmap: Bitmap, activity: Activity) {
    try {
        @Suppress("RestrictedApi")
        androidx.palette.graphics.Palette.from(bitmap).generate { p ->
            p?.let { palette ->
                val dominantColor = palette.getDominantColor(
                    ContextCompat.getColor(context, android.R.color.white)
                )
                (activity as? AppCompatActivity)?.let {
                    it.window?.statusBarColor = dominantColor
                }
            }
        }
    } catch (_: IllegalArgumentException) {
        // bitmap may have been recycled
    }
}

fun AppCompatActivity.setStatusBarColor(@ColorRes colorRes: Int) {
    window?.statusBarColor = ContextCompat.getColor(this, colorRes)
}

fun Context.getActivity(): AppCompatActivity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is AppCompatActivity) {
            return currentContext
        }
        currentContext = currentContext.baseContext
    }
    return null
}
