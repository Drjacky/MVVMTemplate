package app.web.drjackycv.presentation.products.productdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.extension.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product_detail.*

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val safeArgs: ProductDetailFragmentArgs by navArgs()
        val product = safeArgs.productDetailBeerUI

        setSharedElementTransitionOnEnter()
        //postponeEnterTransition()

        with(product) {
            productDetailIdTxv.text = id.toString()
            productDetailImv.transitionName = id.toString()
            productDetailImv.load(url = imageUrl, activity = activity)
            productDetailNameTxv.text = name
            productDetailDescriptionTxv.text = getString(R.string.description, description)
            productDetailAbvTxv.text = getString(R.string.abv, abv.toString())
        }
    }

    private fun setSharedElementTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.shared_element_transition)
    }

}