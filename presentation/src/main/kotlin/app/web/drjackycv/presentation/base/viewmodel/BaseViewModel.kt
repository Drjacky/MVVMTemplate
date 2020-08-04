package app.web.drjackycv.presentation.base.viewmodel

import android.content.res.Resources
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.web.drjackycv.domain.base.Failure
import app.web.drjackycv.presentation.R
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel @ViewModelInject constructor() : ViewModel() {

    @Inject
    lateinit var resources: Resources

    private var mutableLoading: MutableLiveData<Boolean> = MutableLiveData()
    val ldLoading: LiveData<Boolean> = mutableLoading

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun handleFailure(throwable: Throwable, retryAction: () -> Unit): Failure {
        val failure = throwable as? Failure
            ?: Failure.FailureWithMessage(resources.getString(R.string.something_went_wrong))

        failure.retryAction = retryAction
        return failure
    }

    protected fun loading(visible: Boolean) {
        mutableLoading.value = visible
    }

}