package app.web.drjackycv.presentation.extension

import android.view.View
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import app.web.drjackycv.presentation.exception.ReactiveClickException
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

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

internal fun Modifier.fade(visible: Boolean) = composed {
    Modifier.placeholder(
        highlight = PlaceholderHighlight.fade(),
        visible = visible,
    )
}

internal fun Modifier.shimmer(visible: Boolean) = composed {
    Modifier.placeholder(
        highlight = PlaceholderHighlight.shimmer(),
        visible = visible,
    )
}
