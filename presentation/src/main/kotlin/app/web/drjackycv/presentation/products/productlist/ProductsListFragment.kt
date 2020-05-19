package app.web.drjackycv.presentation.products.productlist

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.paging.PagedList
import app.web.drjackycv.domain.base.Failure
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.base.fragment.BaseFragment
import app.web.drjackycv.presentation.extension.gone
import app.web.drjackycv.presentation.extension.observe
import app.web.drjackycv.presentation.extension.viewModel
import app.web.drjackycv.presentation.extension.visible
import kotlinx.android.synthetic.main.fragment_product_list.*
import kotlinx.android.synthetic.main.item_error.*
import kotlinx.android.synthetic.main.progress_bar_circular.*

class ProductsListFragment : BaseFragment() {

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

            getProductsList()

            observe(ldBeersList, ::addProducts)

            observe(ldLoading, ::loadingUI)

            observe(ldFailure, ::handleFailure)

        }
    }

    private fun addProducts(productsList: PagedList<RecyclerItem>) {
        productListRecyclerView.visible()
        productsListAdapter.submitList(productsList)
    }

    private fun loadingUI(isLoading: Boolean) {
        itemErrorContainer.gone()
        if (isLoading) {
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
        val bundle = bundleOf("productDetailBeerUI" to BeerMapper().mapToUI(item as Beer))
        val options = navOptions {
            anim {
                enter = R.anim.enter
                exit = R.anim.exit
                popEnter = R.anim.enter
                popExit = R.anim.exit
            }
        }
        findNavController().navigate(R.id.productDetailFragment, bundle, options)
    }

}