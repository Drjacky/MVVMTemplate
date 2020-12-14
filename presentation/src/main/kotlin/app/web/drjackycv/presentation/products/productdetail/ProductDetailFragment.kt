package app.web.drjackycv.presentation.products.productdetail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.compose.material.*
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.navArgs
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.databinding.FragmentProductDetailBinding
import app.web.drjackycv.presentation.extension.load
import app.web.drjackycv.presentation.extension.viewBinding
import app.web.drjackycv.presentation.base.compose.*
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
        val safeArgs: ProductDetailFragmentArgs by navArgs()
        val product = safeArgs.productDetailBeerUI

        setSharedElementTransitionOnEnter()

        with(product) {
            binding.productDetailIdTxv.text = id.toString()
            binding.productDetailContainer.transitionName = id.toString()
            binding.productDetailImv.load(url = imageUrl, activity = activity)
            binding.productDetailNameTxv.text = name
            binding.productDetailDescriptionTxv.text = getString(R.string.description, description)
            binding.productDetailAbvTxv.text = getString(R.string.abv, abv.toString())
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