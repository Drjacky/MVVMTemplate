package app.web.drjackycv.feature.products.productlist

import androidx.paging.PagingData
import app.web.drjackycv.core.domain.products.usecase.GetProductsListByCoroutineUseCase
import app.web.drjackycv.core.domain.products.usecase.GetProductsListUseCase
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

        val getProductsListByCoroutineUseCase = GetProductsListByCoroutineUseCase {
            fakeRepository.getProductsListByCoroutine()
        }
        val getProductsListUseCase = GetProductsListUseCase {
            fakeRepository.getProductsList()
        }

        viewModel = ProductsListViewModel(
            getProductsUseCase = getProductsListUseCase,
            getProductsListByCoroutineUseCase = getProductsListByCoroutineUseCase,
        )
    }

    @Test
    fun `initial state is Loading`() {
        val state = viewModel.productsListByCoroutine.value
        assertThat(state).isEqualTo(ProductsUiState.Loading)
    }

    @Test
    fun `when products list is emitted, state becomes Success`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.productsListByCoroutine.collect()
        }

        val products = listOf(TestData.testProduct, TestData.testProduct2, TestData.testProduct3)
        fakeRepository.emitProductsList(PagingData.from(products))
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

        fakeRepository.emitProductsList(PagingData.from(emptyList()))
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

        val firstBatch = listOf(TestData.testProduct)
        fakeRepository.emitProductsList(PagingData.from(firstBatch))
        advanceUntilIdle()

        assertThat(viewModel.productsListByCoroutine.value)
            .isInstanceOf(ProductsUiState.Success::class.java)

        val secondBatch = listOf(TestData.testProduct, TestData.testProduct2, TestData.testProduct3)
        fakeRepository.emitProductsList(PagingData.from(secondBatch))
        advanceUntilIdle()

        assertThat(viewModel.productsListByCoroutine.value)
            .isInstanceOf(ProductsUiState.Success::class.java)

        collectJob.cancel()
    }
}
