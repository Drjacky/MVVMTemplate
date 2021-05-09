package app.web.drjackycv.presentation.products.productlist

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
import app.web.drjackycv.domain.base.Failure
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.base.adapter.LoadingStateAdapter
import app.web.drjackycv.presentation.base.adapter.RecyclerItem
import app.web.drjackycv.presentation.databinding.FragmentProductListBinding
import app.web.drjackycv.presentation.extension.*
import app.web.drjackycv.presentation.products.choose.ChoosePathType
import app.web.drjackycv.presentation.products.entity.BeerUI
import com.google.android.material.transition.platform.Hold
import com.google.android.material.transition.platform.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ProductsListFragment : Fragment(R.layout.fragment_product_list) {

    private val binding by viewBinding(FragmentProductListBinding::bind) {
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

    private fun cleanUp(binding: FragmentProductListBinding?) {
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
            productsListAdapter.withLoadStateFooter(LoadingStateAdapter())
        productsListAdapter.addLoadStateListener { adapterLoadingErrorHandling(it) }
        productsListAdapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY

        //productListRecyclerView.itemAnimator = null
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

            viewLifecycleOwner.observe(ldProductsList, ::addProductsList)

            failure.collectIn(viewLifecycleOwner) {
                handleFailure(it)
            }

        }
    }

    private fun setupViewByCoroutine() {
        productsListViewModel.run {
            productsListByCoroutine.collectIn(viewLifecycleOwner) {
                addProductsList(it)
            }

            failure.collectIn(viewLifecycleOwner) {
                handleFailure(it)
            }
        }
    }

    private fun addProductsList(productsList: PagingData<RecyclerItem>) {
        binding.productListRecyclerView.visible()
        productsListAdapter.submitData(lifecycle, productsList)
    }

    private fun loadingUI(isLoading: Boolean) {
        binding.inclItemError.itemErrorContainer.gone()
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
        val itemUI = item as BeerUI
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
        binding.productListRecyclerView.visible()
        productsListAdapter.retry()
    }

    private fun setupErrorItem(failure: Failure) {
        binding.inclItemError.itemErrorMessage.text = failure.msg
        binding.inclItemError.itemErrorRetryBtn.setOnClickListener { failure.retryAction() }
    }

}