package app.web.drjackycv.presentation.extension

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.addRepeatingJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

inline fun <T> Flow<T>.collectIn(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    crossinline action: suspend CoroutineScope.(T) -> Unit,
) = owner.addRepeatingJob(minActiveState, coroutineContext) {
    collect {
        action(it)
    }
}