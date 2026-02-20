package app.web.drjackycv.core.common.base

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.web.drjackycv.core.common.R
import app.web.drjackycv.core.domain.base.Failure
import autodispose2.lifecycle.CorrespondingEventsFunction
import autodispose2.lifecycle.LifecycleEndedException
import autodispose2.lifecycle.LifecycleScopeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel(),
    LifecycleScopeProvider<BaseViewModel.ViewModelEvent> {

    private val lifecycleEventsDelegate =
        lazy { BehaviorSubject.createDefault(ViewModelEvent.CREATED) }

    private val lifecycleEvents by lifecycleEventsDelegate

    @Inject
    lateinit var resources: Resources

    private val _failure: Channel<Failure> = Channel(Channel.BUFFERED)
    val failure: Flow<Failure> = _failure.receiveAsFlow()

    override fun lifecycle(): Observable<ViewModelEvent> {
        return lifecycleEvents.hide()
    }

    override fun correspondingEvents(): CorrespondingEventsFunction<ViewModelEvent>? {
        return CORRESPONDING_EVENTS
    }

    override fun peekLifecycle(): ViewModelEvent? {
        return lifecycleEvents.value
    }

    override fun onCleared() {
        if (lifecycleEventsDelegate.isInitialized()) {
            lifecycleEvents.onNext(ViewModelEvent.CLEARED)
        }
        super.onCleared()
    }

    fun handleFailure(throwable: Throwable, retryAction: () -> Unit) {
        val failure = when (throwable) {
            is Failure.NoInternet -> {
                Failure.NoInternet(
                    msg = resources.getString(R.string.error_no_internet),
                    retryAction = retryAction,
                )
            }

            is Failure.Api -> {
                Failure.Api(
                    msg = throwable.msg,
                    retryAction = retryAction,
                )
            }

            is Failure.Timeout -> {
                Failure.Timeout(
                    msg = resources.getString(R.string.error_timeout),
                    retryAction = retryAction,
                )
            }

            is Failure.Unknown -> {
                Failure.Unknown(
                    msg = resources.getString(R.string.error_unknown),
                    retryAction = retryAction,
                )
            }

            else -> {
                Failure.Unknown(
                    msg = resources.getString(R.string.error_unknown),
                    retryAction = retryAction,
                )
            }
        }

        viewModelScope.launch {
            _failure.send(failure)
        }
    }

    enum class ViewModelEvent {
        CREATED, CLEARED
    }

    companion object {
        private val CORRESPONDING_EVENTS = CorrespondingEventsFunction<ViewModelEvent> { event ->
            when (event) {
                ViewModelEvent.CREATED -> ViewModelEvent.CLEARED
                else -> throw LifecycleEndedException(
                    "Cannot bind to ViewModel lifecycle after onCleared."
                )
            }
        }
    }

}