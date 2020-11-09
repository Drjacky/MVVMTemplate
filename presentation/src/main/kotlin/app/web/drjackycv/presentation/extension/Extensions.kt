package app.web.drjackycv.presentation.extension

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

/*fun <T> Fragment.viewBinding(initialise: () -> T): ReadOnlyProperty<Fragment, T> =
    object : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {*/
class FragmentViewBindingDelegate<T : ViewBinding>(
    val fragment: Fragment,
    val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {

    // A backing property to hold our value
    private var binding: T? = null

    private var viewLifecycleOwner: LifecycleOwner? = null

    init {
        // Observe the View Lifecycle of the Fragment
        this.fragment
            .viewLifecycleOwnerLiveData
            .observe(fragment, Observer { newLifecycleOwner ->
                viewLifecycleOwner
                    ?.lifecycle
                    ?.removeObserver(this)

                viewLifecycleOwner = newLifecycleOwner.also {
                    it.lifecycle.addObserver(this)
                }
            })
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        binding = null

    }

    override fun getValue(
        thisRef: Fragment,
        property: KProperty<*>
    ): T {
        // Return the backing property if it's set, or initialise
        return this.binding ?: viewBindingFactory(fragment.requireView()).also {
            this.binding = it
        }
    }
}

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }