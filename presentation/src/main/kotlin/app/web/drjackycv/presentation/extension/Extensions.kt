package app.web.drjackycv.presentation.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.web.drjackycv.presentation.base.viewmodel.BaseViewModel

inline fun <reified T : BaseViewModel> Fragment.viewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit = {}
): T {
    val vm = ViewModelProvider(this, factory).get(T::class.java)
    vm.body()

    return vm
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}