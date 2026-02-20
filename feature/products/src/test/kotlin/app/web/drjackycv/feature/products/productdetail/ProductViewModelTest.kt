package app.web.drjackycv.feature.products.productdetail

import app.web.drjackycv.core.domain.products.usecase.GetBeerByCoroutineUseCase
import app.web.drjackycv.core.domain.products.usecase.GetBeerUseCase
import app.web.drjackycv.core.testing.data.TestData
import app.web.drjackycv.core.testing.products.FakeProductsListRepository
import app.web.drjackycv.core.testing.rule.TestDispatcherRule
import app.web.drjackycv.feature.products.entity.mapIt
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
class ProductViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private lateinit var fakeRepository: FakeProductsListRepository
    private lateinit var viewModel: ProductViewModel

    @Before
    fun setup() {
        fakeRepository = FakeProductsListRepository()
        fakeRepository.addBeer(TestData.testBeer)
        fakeRepository.addBeer(TestData.testBeer2)

        val getBeerByCoroutineUseCase = GetBeerByCoroutineUseCase { id ->
            fakeRepository.getBeerByCoroutine(id)
        }
        val getBeerUseCase = GetBeerUseCase { id ->
            fakeRepository.getBeer(id)
        }

        viewModel = ProductViewModel(
            getBeerUseCase = getBeerUseCase,
            getBeerByCoroutineUseCase = getBeerByCoroutineUseCase,
        )
    }

    @Test
    fun `initial state is Loading`() {
        val state = viewModel.productByCoroutine.value
        assertThat(state).isEqualTo(ProductUiState.Loading)
    }

    @Test
    fun `when product id is set, state becomes Success with correct data`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.productByCoroutine.collect()
        }

        viewModel.setProductId(TestData.testBeer.id.toString())
        advanceUntilIdle()

        val state = viewModel.productByCoroutine.value
        assertThat(state).isInstanceOf(ProductUiState.Success::class.java)

        val successState = state as ProductUiState.Success
        assertThat(successState.item).isNotNull()
        assertThat(successState.item?.name).isEqualTo(TestData.testBeer.name)
        assertThat(successState.item?.id).isEqualTo(TestData.testBeer.id)

        collectJob.cancel()
    }

    @Test
    fun `when product id is set to nonexistent, state becomes Error`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.productByCoroutine.collect()
        }

        viewModel.setProductId("999")
        advanceUntilIdle()

        val state = viewModel.productByCoroutine.value
        assertThat(state).isInstanceOf(ProductUiState.Error::class.java)

        collectJob.cancel()
    }

    @Test
    fun `when repository fails, state becomes Error`() = runTest {
        fakeRepository.shouldFail = true

        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.productByCoroutine.collect()
        }

        viewModel.setProductId(TestData.testBeer.id.toString())
        advanceUntilIdle()

        val state = viewModel.productByCoroutine.value
        assertThat(state).isInstanceOf(ProductUiState.Error::class.java)

        collectJob.cancel()
    }

    @Test
    fun `setProductId updates the product when called multiple times`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.productByCoroutine.collect()
        }

        viewModel.setProductId(TestData.testBeer.id.toString())
        advanceUntilIdle()

        var state = viewModel.productByCoroutine.value as ProductUiState.Success
        assertThat(state.item?.name).isEqualTo(TestData.testBeer.name)

        viewModel.setProductId(TestData.testBeer2.id.toString())
        advanceUntilIdle()

        state = viewModel.productByCoroutine.value as ProductUiState.Success
        assertThat(state.item?.name).isEqualTo(TestData.testBeer2.name)

        collectJob.cancel()
    }

    @Test
    fun `Success state maps Beer to BeerUI correctly`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.productByCoroutine.collect()
        }

        viewModel.setProductId(TestData.testBeer.id.toString())
        advanceUntilIdle()

        val state = viewModel.productByCoroutine.value as ProductUiState.Success
        val expectedUI = TestData.testBeer.mapIt()
        assertThat(state.item?.id).isEqualTo(expectedUI.id)
        assertThat(state.item?.name).isEqualTo(expectedUI.name)
        assertThat(state.item?.status).isEqualTo(expectedUI.status)
        assertThat(state.item?.species).isEqualTo(expectedUI.species)
        assertThat(state.item?.gender).isEqualTo(expectedUI.gender)
        assertThat(state.item?.image).isEqualTo(expectedUI.image)

        collectJob.cancel()
    }
}
