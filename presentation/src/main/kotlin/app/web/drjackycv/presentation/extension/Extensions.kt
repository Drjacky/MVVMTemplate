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

class FragmentViewBindingDelegate<T : ViewBinding>(
    val fragment: Fragment,
    val viewBindingFactory: (View) -> T,
    val cleanUp: ((T?) -> Unit)?,
) : ReadOnlyProperty<Fragment, T> {

    // A backing property to hold our value
    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            val viewLifecycleOwnerObserver = Observer<LifecycleOwner?> { owner ->
                if (owner == null) {
                    cleanUp?.invoke(binding)
                    binding = null
                }
            }

            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observeForever(
                    viewLifecycleOwnerObserver
                )
            }

            override fun onDestroy(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.removeObserver(
                    viewLifecycleOwnerObserver
                )
            }
        })
    }

    override fun getValue(
        thisRef: Fragment,
        property: KProperty<*>,
    ): T {
        val binding = binding
        if (binding != null && binding.root === thisRef.view) {
            return binding
        }

        val view = thisRef.view
            ?: throw IllegalStateException("Should not attempt to get bindings when Fragment's view is null.")

        return viewBindingFactory(view).also { this.binding = it }
    }
}

inline fun <T : ViewBinding> Fragment.viewBinding(
    crossinline viewBindingFactory: (View) -> T,
    noinline cleanUp: ((T?) -> Unit)? = null,
): FragmentViewBindingDelegate<T> =
    FragmentViewBindingDelegate(this, { v -> viewBindingFactory(v) }, cleanUp)

inline fun <T : ViewBinding> Fragment.viewInflateBinding(
    crossinline bindingInflater: (LayoutInflater) -> T,
    noinline cleanUp: ((T?) -> Unit)? = null,
): FragmentViewBindingDelegate<T> =
    FragmentViewBindingDelegate(
        fragment = this,
        viewBindingFactory = { v -> bindingInflater(LayoutInflater.from(v.context)) },
        cleanUp = cleanUp
    )

inline fun <T : ViewBinding> AppCompatActivity.viewInflateBinding(
    crossinline bindingInflater: (LayoutInflater) -> T,
) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }