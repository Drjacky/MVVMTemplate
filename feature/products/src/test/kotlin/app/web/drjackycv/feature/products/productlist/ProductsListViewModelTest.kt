package app.web.drjackycv.feature.products.productlist

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import app.web.drjackycv.core.domain.products.usecase.GetBeersListByCoroutineUseCase
import app.web.drjackycv.core.domain.products.usecase.GetBeersListUseCase
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
        val getBeersListByCoroutineUseCase = GetBeersListByCoroutineUseCase {
            fakeRepository.getBeersListByCoroutine()
        }
        val getBeersListUseCase = GetBeersListUseCase {
            fakeRepository.getBeersList()
        }
        val savedStateHandle = SavedStateHandle(
            mapOf(CHOOSE_PATH_TYPE to path)
        )

        return ProductsListViewModel(
            getBeersUseCase = getBeersListUseCase,
            getBeersListByCoroutineUseCase = getBeersListByCoroutineUseCase,
            savedStateHandle = savedStateHandle,
        )
    }

    @Test
    fun `coroutine path - when beers list is emitted, productsListByCoroutine updates`() =
        runTest {
            val beers = listOf(TestData.testBeer, TestData.testBeer2, TestData.testBeer3)
            fakeRepository.emitBeersList(PagingData.from(beers))

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
            fakeRepository.emitBeersList(PagingData.from(emptyList()))

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
