package app.web.drjackycv.presentation.products.productlist

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import app.web.drjackycv.domain.base.Failure
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.base.adapter.LoadingStateAdapter
import app.web.drjackycv.presentation.extension.gone
import app.web.drjackycv.presentation.extension.invisible
import app.web.drjackycv.presentation.extension.observe
import app.web.drjackycv.presentation.extension.visible
import com.google.android.material.transition.platform.Hold
import com.google.android.material.transition.platform.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product_list.*
import kotlinx.android.synthetic.main.item_error.*
import kotlinx.android.synthetic.main.item_loading.*

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
        productListRecyclerView.adapter =
            productsListAdapter.withLoadStateFooter(LoadingStateAdapter())
        productsListAdapter.addLoadStateListener { adapterLoadingErrorHandling(it) }

        //productListRecyclerView.itemAnimator = null
        postponeEnterTransition()
        view?.doOnPreDraw { startPostponedEnterTransition() }
    }

    private fun setupViewModel() {
        productsListViewModel.run {

            observe(ldProductsList, ::addProductsList)

            observe(ldFailure, ::handleFailure)

        }
    }

    private fun addProductsList(productsList: PagingData<RecyclerItem>) {
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
            else -> {
                productListRecyclerView.gone()
                itemErrorContainer.visible()
                itemErrorMessage.text = failure.message
                itemErrorRetryBtn.invisible()
            }
        }
    }

    private fun navigateToProductDetail(item: RecyclerItem, view: View) {
        val itemUI = BeerMapper().mapToUI(item as Beer)
        val action =
            ProductsListFragmentDirections.navigateToProductDetailFragment(itemUI)
        val extras = FragmentNavigatorExtras(
            view to itemUI.id.toString()
        )
        exitTransition = Hold().apply {
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.motion_duration_small).toLong()
        }
        findNavController().navigate(action, extras)
    }

    private fun adapterLoadingErrorHandling(combinedLoadStates: CombinedLoadStates) {
        if (combinedLoadStates.refresh is LoadState.Loading) {
            loadingUI(true)
        } else {
            loadingUI(false)
            val error = when {
                combinedLoadStates.prepend is LoadState.Error -> combinedLoadStates.prepend as LoadState.Error
                combinedLoadStates.source.prepend is LoadState.Error -> combinedLoadStates.prepend as LoadState.Error
                combinedLoadStates.append is LoadState.Error -> combinedLoadStates.append as LoadState.Error
                combinedLoadStates.source.append is LoadState.Error -> combinedLoadStates.append as LoadState.Error
                combinedLoadStates.refresh is LoadState.Error -> combinedLoadStates.refresh as LoadState.Error
                else -> null
            }
            error?.run {
                productsListViewModel.handleFailure(this.error) { retryFetchData() }
            }
        }
    }

    private fun retryFetchData() {
        productsListViewModel.getProducts("")
    }

}