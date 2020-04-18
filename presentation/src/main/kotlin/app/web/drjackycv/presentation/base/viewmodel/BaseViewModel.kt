package app.web.drjackycv.presentation.base.viewmodel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.web.drjackycv.domain.base.usecase.Failure
import app.web.drjackycv.presentation.R
import dagger.Lazy
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var resources: Lazy<Resources>

    private var mutableFailure: MutableLiveData<Failure> = MutableLiveData()
    val ldFailure: LiveData<Failure> = mutableFailure
    private var mutableLoading: MutableLiveData<Boolean> = MutableLiveData()
    val ldLoading: LiveData<Boolean> = mutableLoading

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    private fun getFailure(throwable: Throwable, retryAction: () -> Unit): Failure {
        val failure = throwable as? Failure ?: Failure.FailureWithMessage(
            resources.get().getString(R.string.something_went_wrong)
        )
        failure.retryAction = retryAction
        return failure
    }

    protected fun handleFailure(throwable: Throwable, retryAction: () -> Unit) {
        val failure = getFailure(throwable, retryAction)
        mutableFailure.value = failure
    }

    protected fun loading(visible: Boolean) {
        mutableLoading.value = visible
    }

}