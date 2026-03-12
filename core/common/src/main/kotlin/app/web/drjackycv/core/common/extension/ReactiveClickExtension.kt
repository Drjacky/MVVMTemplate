package app.web.drjackycv.core.common.extension

import android.view.View
import app.web.drjackycv.core.common.exception.ReactiveClickException
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

fun View.setOnReactiveClickListener(action: () -> Unit) {
    clicks()
        .throttleFirst(1, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { action() },
            { e ->
                Timber.e(
                    ReactiveClickException(
                        this.javaClass.name,
                        e,
                        e.stackTrace
                    )
                )
            },
        )
}
