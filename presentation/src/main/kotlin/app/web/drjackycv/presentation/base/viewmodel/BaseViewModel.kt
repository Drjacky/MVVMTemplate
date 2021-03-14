package app.web.drjackycv.presentation.base.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.web.drjackycv.domain.base.Failure
import app.web.drjackycv.presentation.R
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.FlowableSubscribeProxy
import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleEndedException
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.subjects.BehaviorSubject
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
                Failure.NoInternet(resources.getString(R.string.error_no_internet))
            }
            is Failure.Timeout -> {
                Failure.Timeout(resources.getString(R.string.error_timeout))
            }
            is Failure.Unknown -> {
                Failure.Unknown(resources.getString(R.string.error_unknown))
            }
            else -> {
                Failure.Unknown(resources.getString(R.string.error_unknown))
            }
        }

        failure.retryAction = retryAction
        viewModelScope.launch {
            _failure.send(failure)
        }
    }

    @CheckReturnValue
    fun <T> Flowable<T>.autoDisposable(): FlowableSubscribeProxy<T> =
        this.`as`(AutoDispose.autoDisposable(this@BaseViewModel))


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