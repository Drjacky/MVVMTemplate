package app.web.drjackycv.feature.products.productlist

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import app.web.drjackycv.core.domain.products.usecase.GetBeersListByCoroutineUseCase
import app.web.drjackycv.core.domain.products.usecase.GetBeersListUseCase
import app.web.drjackycv.core.testing.data.TestData
import app.web.drjackycv.core.testing.products.FakeProductsListRepository
import app.web.drjackycv.core.testing.rule.TestDispatcherRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductsListViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private lateinit var fakeRepository: FakeProductsListRepository
    private lateinit var viewModel: ProductsListViewModel

    @Before
    fun setup() {
        fakeRepository = FakeProductsListRepository()

        val getBeersListByCoroutineUseCase = GetBeersListByCoroutineUseCase {
            fakeRepository.getBeersListByCoroutine()
        }
        val getBeersListUseCase = GetBeersListUseCase {
            fakeRepository.getBeersList()
        }

        viewModel = ProductsListViewModel(
            getBeersUseCase = getBeersListUseCase,
            getBeersListByCoroutineUseCase = getBeersListByCoroutineUseCase,
            savedStateHandle = SavedStateHandle(),
        )
    }

    @Test
    fun `initial state is Loading`() {
        val state = viewModel.productsListByCoroutine.value
        assertThat(state).isEqualTo(ProductsUiState.Loading)
    }

    @Test
    fun `when beers list is emitted, state becomes Success`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.productsListByCoroutine.collect()
        }

        val beers = listOf(TestData.testBeer, TestData.testBeer2, TestData.testBeer3)
        fakeRepository.emitBeersList(PagingData.from(beers))
        advanceUntilIdle()

        val state = viewModel.productsListByCoroutine.value
        assertThat(state).isInstanceOf(ProductsUiState.Success::class.java)

        collectJob.cancel()
    }

    @Test
    fun `when empty list is emitted, state becomes Success with empty data`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.productsListByCoroutine.collect()
        }

        fakeRepository.emitBeersList(PagingData.from(emptyList()))
        advanceUntilIdle()

        val state = viewModel.productsListByCoroutine.value
        assertThat(state).isInstanceOf(ProductsUiState.Success::class.java)

        collectJob.cancel()
    }

    @Test
    fun `when new data is emitted, state updates to new Success`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.productsListByCoroutine.collect()
        }

        val firstBatch = listOf(TestData.testBeer)
        fakeRepository.emitBeersList(PagingData.from(firstBatch))
        advanceUntilIdle()

        assertThat(viewModel.productsListByCoroutine.value)
            .isInstanceOf(ProductsUiState.Success::class.java)

        val secondBatch = listOf(TestData.testBeer, TestData.testBeer2, TestData.testBeer3)
        fakeRepository.emitBeersList(PagingData.from(secondBatch))
        advanceUntilIdle()

        assertThat(viewModel.productsListByCoroutine.value)
            .isInstanceOf(ProductsUiState.Success::class.java)

        collectJob.cancel()
    }
}
