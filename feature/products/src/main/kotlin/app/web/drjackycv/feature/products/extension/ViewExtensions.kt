package app.web.drjackycv.feature.products.extension

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
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
    cardView: CardView? = null,
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

    val glideRequest = Glide.with(context).setDefaultRequestOptions(requestOptions).load(url)

    if (activity != null || cardView != null) {
        glideRequest.into(object : CustomTarget<Drawable>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?,
            ) {
                setImageDrawable(resource)
                val bitmap = (resource as? BitmapDrawable)?.bitmap
                if (bitmap != null && !bitmap.isRecycled) {
                    if (activity != null) {
                        handleStatusBarPalette(bitmap, activity)
                    }
                    if (cardView != null) {
                        handleCardPalette(bitmap, cardView)
                    }
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

private fun handleStatusBarPalette(bitmap: Bitmap, activity: Activity) {
    try {
        Palette.from(bitmap).generate { p ->
            p?.let { palette ->
                val dominantColor = palette.getDominantColor(
                    ContextCompat.getColor(activity, android.R.color.white)
                )
                (activity as? AppCompatActivity)?.window?.statusBarColor = dominantColor
            }
        }
    } catch (_: IllegalArgumentException) {
        // bitmap may have been recycled
    }
}

private fun handleCardPalette(bitmap: Bitmap, cardView: CardView) {
    try {
        val nightMask = cardView.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val isDarkMode = nightMask == Configuration.UI_MODE_NIGHT_YES

        Palette.from(bitmap).generate { p ->
            p?.let { palette ->
                val swatch = if (isDarkMode) {
                    palette.darkVibrantSwatch
                } else {
                    palette.lightVibrantSwatch
                }
                swatch?.let { cardView.setCardBackgroundColor(it.rgb) }
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
