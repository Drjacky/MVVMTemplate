package app.web.drjackycv.feature.products.productdetail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import app.web.drjackycv.feature.products.R
import app.web.drjackycv.feature.products.databinding.FeatureProductsFragmentProductDetailBinding
import app.web.drjackycv.feature.products.extension.load
import app.web.drjackycv.feature.products.extension.viewBinding
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.feature_products_fragment_product_detail) {

    private val binding by viewBinding(FeatureProductsFragmentProductDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val safeArgs: ProductDetailFragmentArgs by navArgs()
        val product = safeArgs.productDetailProductUI

        setSharedElementTransitionOnEnter()

        with(product) {
            binding.productDetailIdTxv.text = id.toString()
            binding.productDetailContainer.transitionName = id.toString()
            binding.productDetailImv.load(url = image, activity = activity)
            binding.productDetailNameTxv.text = name
            binding.productDetailInfoTxv.text =
                getString(R.string.character_info, species, status, gender)
            binding.productDetailLocationTxv.text =
                getString(R.string.character_location, location.name)
        }
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
