package app.web.drjackycv.presentation.products.productdetail

import android.os.Bundle
import android.view.View
import app.web.drjackycv.domain.base.usecase.Failure
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.base.fragment.BaseFragment
import app.web.drjackycv.presentation.extension.*
import app.web.drjackycv.presentation.products.entity.ProductUI
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.android.synthetic.main.item_error.*
import kotlinx.android.synthetic.main.progress_bar_circular.*

private const val PRODUCT_ID = "PRODUCT_ID"

class ProductDetailFragment : BaseFragment() {

    companion object {
        fun getFragment(productId: Int): ProductDetailFragment =
            ProductDetailFragment().apply {
                setParams(PRODUCT_ID to productId)
            }
    }

    override var fragmentLayout: Int = R.layout.fragment_product_detail

    private lateinit var productDetailViewModel: ProductDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemErrorContainer.gone()
        setupViewModel()
    }

    private fun setupViewModel() {
        val productId: Int = getSerializableParam(tag = PRODUCT_ID)
        productDetailViewModel = viewModel(viewModelFactory.get()) {
            getProductDetails(productId)

            observe(ldLoading, ::loadingUI)

            observe(ldProductDetail, ::setupUI)

            observe(ldFailure, ::handleFailure)

        }

    }

    private fun loadingUI(isLoading: Boolean) {
        if (isLoading) {
            itemErrorContainer.gone()
            progressBar.visible()
        } else {
            progressBar.gone()
        }
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.FailureWithMessage -> {
                doVisibleGoneDetailContainer(View.GONE)
                itemErrorContainer.visible()
                itemErrorMessage.text = failure.msg
                itemErrorRetryBtn.setOnClickListener { failure.retryAction() }
            }
        }
    }

    private fun setupUI(productUI: ProductUI) {
        doVisibleGoneDetailContainer(View.VISIBLE)
        itemErrorContainer.gone()
        with(productUI) {
            productDetailImv.load(imageUrl)
            productDetailTitleTxv.text = title
            productDetailDescription.text = getString(R.string.description, description)
            productDetailAllergyTxv.text = getString(R.string.allergies, allergyInformation)
        }
    }

    private fun doVisibleGoneDetailContainer(visibility: Int) {
        when (visibility) {
            View.VISIBLE -> {
                productDetailImv.visible()
                productDetailTitleTxv.visible()
                productDetailDescription.visible()
                productDetailAllergyTxv.visible()
            }
            View.GONE -> {
                productDetailImv.gone()
                productDetailTitleTxv.gone()
                productDetailDescription.gone()
                productDetailAllergyTxv.gone()
            }
        }
    }

}