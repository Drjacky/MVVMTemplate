package app.web.drjackycv.presentation.products.productdetail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.databinding.FragmentProductDetailBinding
import app.web.drjackycv.presentation.extension.viewBinding
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private val binding by viewBinding(FragmentProductDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val composeView = ComposeView(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            setContent {
                ProductDetailComposable {
                    MyScreenContent("Buzz")
                }
            }
        }
        setupUI()
        binding.productDetailContainer.addView(composeView)
    }

    private fun setupUI() {
        /*val safeArgs: ProductDetailFragmentArgs by navArgs()
        val product = safeArgs.productDetailBeerUI

        setSharedElementTransitionOnEnter()

        with(product) {
            binding.productDetailIdTxv.text = id.toString()
            binding.productDetailContainer.transitionName = id.toString()
            binding.productDetailImv.load(url = imageUrl, activity = activity)
            binding.productDetailNameTxv.text = name
            binding.productDetailDescriptionTxv.text = getString(R.string.description, description)
            binding.productDetailAbvTxv.text = getString(R.string.abv, abv.toString())
        }*/
    }

    private fun setSharedElementTransitionOnEnter() {
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 400L
            isElevationShadowEnabled = true
            pathMotion = MaterialArcMotion()
            startElevation = 9f
            endElevation = 9f
            scrimColor = Color.TRANSPARENT
        }
    }

}