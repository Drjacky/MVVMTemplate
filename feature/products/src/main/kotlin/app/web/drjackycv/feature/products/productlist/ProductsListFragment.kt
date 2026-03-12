package app.web.drjackycv.feature.products.productlist

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
import app.web.drjackycv.core.common.extension.collectIn
import app.web.drjackycv.core.common.extension.gone
import app.web.drjackycv.core.common.extension.invisible
import app.web.drjackycv.core.common.extension.viewBinding
import app.web.drjackycv.core.common.extension.visible
import app.web.drjackycv.core.domain.base.Failure
import app.web.drjackycv.feature.products.R
import app.web.drjackycv.feature.products.base.adapter.RecyclerItem
import app.web.drjackycv.feature.products.choose.ChoosePathType
import app.web.drjackycv.feature.products.databinding.FeatureProductsFragmentProductListBinding
import app.web.drjackycv.feature.products.entity.ProductUI
import com.google.android.material.transition.platform.Hold
import com.google.android.material.transition.platform.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ProductsListFragment : Fragment(R.layout.feature_products_fragment_product_list) {

    private val binding by viewBinding(FeatureProductsFragmentProductListBinding::bind) {
        cleanUp(it)
    }
    private val productsListViewModel: ProductsListViewModel by viewModels()
    private var path = ChoosePathType.RX

    private val productsListAdapter: ProductsListAdapter by lazy {
        ProductsListAdapter(::navigateToProductDetail)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPath()
        setupRecycler()
    }

    private fun cleanUp(binding: FeatureProductsFragmentProductListBinding?) {
        binding?.productListRecyclerView?.adapter = null
    }

    private fun setupPath() {
        val safeArgs: ProductsListFragmentArgs by navArgs()
        path = safeArgs.choosePathType
        Timber.d("Which path: $path")
        setupViewBaseOnPath()
    }

    private fun setupRecycler() {
        binding.inclItemError.itemErrorContainer.gone()
        binding.productListRecyclerView.adapter =
            productsListAdapter.withLoadStateFooter(
                app.web.drjackycv.feature.products.base.adapter.LoadingStateAdapter()
            )
        productsListAdapter.addLoadStateListener { adapterLoadingErrorHandling(it) }
        productsListAdapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY

        postponeEnterTransition()
        view?.doOnPreDraw { startPostponedEnterTransition() }
    }

    private fun setupViewBaseOnPath() {
        when (path) {
            ChoosePathType.RX -> {
                setupView()
            }

            ChoosePathType.COROUTINE -> {
                setupViewByCoroutine()
            }
        }
    }

    private fun setupView() {
        productsListViewModel.run {
            productsListByRx.collectIn(viewLifecycleOwner) {
                addProductsList(it)
            }
        }
    }

    private fun setupViewByCoroutine() {
        productsListViewModel.run {
            productsListByCoroutine.collectIn(viewLifecycleOwner) {
                addProductsList(it)
            }
        }
    }

    private fun addProductsList(productsList: PagingData<RecyclerItem>) {
        loadingUI(false)
        binding.productListRecyclerView.visible()
        productsListAdapter.submitData(lifecycle, productsList)
    }

    private fun loadingUI(isLoading: Boolean) {
        if (isLoading) {
            binding.inclItemLoading.itemLoadingContainer.visible()
        } else {
            binding.inclItemLoading.itemLoadingContainer.gone()
        }
    }

    private fun handleFailure(failure: Failure) {
        binding.productListRecyclerView.gone()
        binding.inclItemError.itemErrorContainer.visible()
        when (failure) {
            is Failure.NoInternet, is Failure.Api, is Failure.Timeout -> {
                setupErrorItem(failure)
            }

            is Failure.Unknown -> {
                setupErrorItem(failure)
            }

            else -> {
                binding.inclItemError.itemErrorMessage.text = failure.message
                binding.inclItemError.itemErrorRetryBtn.invisible()
            }
        }
    }

    private fun navigateToProductDetail(item: RecyclerItem, view: View) {
        val itemUI = item as ProductUI
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
            binding.inclItemError.itemErrorContainer.gone()
            loadingUI(true)
        } else {
            binding.inclItemError.itemErrorContainer.gone()
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
                binding.inclItemError.itemErrorContainer.visible()
                loadingUI(false)
                val failure =
                    productsListViewModel.mapToFailure(this.error) { retryFetchData() }
                handleFailure(failure)
            }
        }
    }

    private fun retryFetchData() {
        binding.inclItemError.itemErrorContainer.gone()
        loadingUI(false)
        binding.productListRecyclerView.visible()
        productsListAdapter.retry()
    }

    private fun setupErrorItem(failure: Failure) {
        binding.inclItemError.itemErrorMessage.text = failure.msg
        binding.inclItemError.itemErrorRetryBtn.setOnClickListener { failure.retryAction() }
    }
}
