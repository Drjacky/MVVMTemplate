package app.web.drjackycv.presentation.products.productlist

import android.os.Bundle
import android.view.View
import androidx.transition.Fade
import app.web.drjackycv.domain.base.usecase.Failure
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.base.adapter.RecyclerItem
import app.web.drjackycv.presentation.base.fragment.BaseFragment
import app.web.drjackycv.presentation.extension.gone
import app.web.drjackycv.presentation.extension.observe
import app.web.drjackycv.presentation.extension.viewModel
import app.web.drjackycv.presentation.extension.visible
import app.web.drjackycv.presentation.products.entity.ItemUI
import app.web.drjackycv.presentation.products.productdetail.ProductDetailFragment
import kotlinx.android.synthetic.main.fragment_product_list.*
import kotlinx.android.synthetic.main.item_error.*
import kotlinx.android.synthetic.main.progress_bar_circular.*

class ProductsListFragment : BaseFragment() {

    companion object {
        fun getFragment(): ProductsListFragment = ProductsListFragment()
    }

    override var fragmentLayout: Int = R.layout.fragment_product_list

    private lateinit var productsListViewModel: ProductsListViewModel

    private val productsListAdapter: ProductsListAdapter by lazy {
        ProductsListAdapter(::navigateToProductDetail)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupViewModel()
    }

    private fun setupRecycler() {
        itemErrorContainer.gone()
        productListRecyclerView.adapter = productsListAdapter
    }

    private fun setupViewModel() {
        productsListViewModel = viewModel(viewModelFactory.get()) {

            observe(ldItemsList, ::addProducts)

            observe(ldLoading, ::loadingUI)

            observe(ldFailure, ::handleFailure)

        }
    }

    private fun addProducts(productsList: List<RecyclerItem>) {
        itemErrorContainer.gone()
        productListRecyclerView.visible()
        productsListAdapter.submitList(productsList)
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
                productListRecyclerView.gone()
                itemErrorContainer.visible()
                itemErrorMessage.text = failure.msg
                itemErrorRetryBtn.setOnClickListener { failure.retryAction() }
            }
        }
    }

    private fun navigateToProductDetail(item: RecyclerItem) {
        val productDetailFragment = ProductDetailFragment.getFragment((item as ItemUI).id)
        productDetailFragment.enterTransition = Fade()
        productDetailFragment.exitTransition = Fade()
        pushStack(productDetailFragment)
    }

}