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

    private var mutableFailure: MutableLiveData<Failure> = MutableLiveData()
    val ldFailure: LiveData<Failure> = mutableFailure

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun handleFailure(throwable: Throwable, retryAction: () -> Unit) {
        val failure = throwable as? Failure
            ?: Failure.FailureWithMessage(resources.getString(R.string.something_went_wrong))

        failure.retryAction = retryAction
        mutableFailure.value = failure
    }

}