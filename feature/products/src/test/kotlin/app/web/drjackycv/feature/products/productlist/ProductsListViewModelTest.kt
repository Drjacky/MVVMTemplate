package app.web.drjackycv.feature.products.productlist

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import app.web.drjackycv.core.domain.products.usecase.GetProductsListByCoroutineUseCase
import app.web.drjackycv.core.domain.products.usecase.GetProductsListUseCase
import app.web.drjackycv.core.testing.data.TestData
import app.web.drjackycv.core.testing.products.FakeProductsListRepository
import app.web.drjackycv.core.testing.rule.TestDispatcherRule
import app.web.drjackycv.feature.products.choose.ChoosePathType
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

    @Before
    fun setup() {
        fakeRepository = FakeProductsListRepository()
    }

    private fun createViewModel(
        path: ChoosePathType = ChoosePathType.COROUTINE,
    ): ProductsListViewModel {
        val getProductsListByCoroutineUseCase = GetProductsListByCoroutineUseCase {
            fakeRepository.getProductsListByCoroutine()
        }
        val getProductsListUseCase = GetProductsListUseCase {
            fakeRepository.getProductsList()
        }
        val savedStateHandle = SavedStateHandle(
            mapOf(CHOOSE_PATH_TYPE to path)
        )

        return ProductsListViewModel(
            getProductsUseCase = getProductsListUseCase,
            getProductsListByCoroutineUseCase = getProductsListByCoroutineUseCase,
            savedStateHandle = savedStateHandle,
        )
    }

    @Test
    fun `coroutine path - when products list is emitted, productsListByCoroutine updates`() =
        runTest {
            val products = listOf(TestData.testProduct, TestData.testProduct2, TestData.testProduct3)
            fakeRepository.emitProductsList(PagingData.from(products))

            val viewModel = createViewModel(ChoosePathType.COROUTINE)

            val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
                viewModel.productsListByCoroutine.collect()
            }

            advanceUntilIdle()

            val state = viewModel.productsListByCoroutine
            assertThat(state).isNotNull()

            collectJob.cancel()
        }

    @Test
    fun `coroutine path - when empty list is emitted, productsListByCoroutine updates`() =
        runTest {
            fakeRepository.emitProductsList(PagingData.from(emptyList()))

            val viewModel = createViewModel(ChoosePathType.COROUTINE)

            val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
                viewModel.productsListByCoroutine.collect()
            }

            advanceUntilIdle()

            val state = viewModel.productsListByCoroutine
            assertThat(state).isNotNull()

            collectJob.cancel()
        }
}
