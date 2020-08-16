package app.web.drjackycv.presentation.products.productlist

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import app.web.drjackycv.domain.base.Failure
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.extension.gone
import app.web.drjackycv.presentation.extension.observe
import app.web.drjackycv.presentation.extension.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product_list.*
import kotlinx.android.synthetic.main.item_error.*
import kotlinx.android.synthetic.main.progress_bar_circular.*

@AndroidEntryPoint
class ProductsListFragment : Fragment(R.layout.fragment_product_list) {

    private val productsListViewModel: ProductsListViewModel by viewModels()

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
        //productListRecyclerView.itemAnimator = null
        postponeEnterTransition()
        productListRecyclerView.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    private fun setupViewModel() {
        productsListViewModel.run {

            observe(ldProductsList, ::addProductsList)

            observe(ldLoading, ::loadingUI)

            //observe(ldFailure, ::handleFailure)

        }
    }

    private fun addProductsList(productsList: PagingData<RecyclerItem>) {
        loadingUI(false)
        productListRecyclerView.visible()
        productsListAdapter.submitData(lifecycle, productsList)
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

    private fun navigateToProductDetail(item: RecyclerItem, imageView: ImageView) {
        val itemUI = BeerMapper().mapToUI(item as Beer)
        val action =
            ProductsListFragmentDirections.navigateToProductDetailFragment(itemUI)
        val extras = FragmentNavigatorExtras(
            imageView to itemUI.id.toString()
        )
        findNavController().navigate(action, extras)
    }

}