package app.web.drjackycv.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.web.drjackycv.presentation.R
import dagger.Lazy
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    @setparam:LayoutRes
    abstract var fragmentLayout: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(fragmentLayout, container, false)

    protected fun pushStack(fragment: Fragment) {
        activity?.let {
            if (it.isFinishing.not()) {
                fragmentManager
                    ?.beginTransaction()
                    ?.addToBackStack(null)
                    ?.add(R.id.parentContainer, fragment)
                    ?.commit()
            }
        }
    }

}