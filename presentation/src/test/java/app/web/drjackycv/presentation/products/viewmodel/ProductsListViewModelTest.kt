package app.web.drjackycv.presentation.products.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.domain.products.factory.ProductFactory
import app.web.drjackycv.domain.products.usecase.GetBeersListByCoroutineParams
import app.web.drjackycv.domain.products.usecase.GetBeersListByCoroutineUseCase
import app.web.drjackycv.domain.products.usecase.GetBeersListParams
import app.web.drjackycv.domain.products.usecase.GetBeersListUseCase
import app.web.drjackycv.presentation.base.adapter.RecyclerItem
import app.web.drjackycv.presentation.products.choose.ChoosePathType
import app.web.drjackycv.presentation.products.productlist.CHOOSE_PATH_TYPE
import app.web.drjackycv.presentation.products.productlist.ProductsListViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString


@RunWith(JUnit4::class)
class ProductsListViewModelTest {

    private lateinit var viewModel: ProductsListViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getBeersListUseCase: GetBeersListUseCase

    @MockK
    lateinit var getBeersListByCoroutineUseCase: GetBeersListByCoroutineUseCase

    @MockK
    lateinit var savedStateHandle: SavedStateHandle

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test when ProductsListViewModel is initialized, products are fetched`() =
        runTest {
            // Given
            val givenProducts = ProductFactory.createProducts(3)
            val flow = flow<PagingData<Beer>> {
//                emit(LoadState.Loading)
                delay(10)
                emit(PagingData.from(givenProducts))
            }
            val productsListObserver = mockk<Observer<PagingData<RecyclerItem>>>(relaxed = true)

            // When
            coEvery {
                getBeersListUseCase.invoke(GetBeersListParams(anyString()))
                getBeersListByCoroutineUseCase.invoke(GetBeersListByCoroutineParams(anyString()))
            }
                .returns(flow)

            // Invoke
            every {
                savedStateHandle.get<ChoosePathType>(CHOOSE_PATH_TYPE) ?: ChoosePathType.COROUTINE
            } returns ChoosePathType.COROUTINE
            viewModel = ProductsListViewModel(
                getBeersUseCase = getBeersListUseCase,
                getBeersListByCoroutineUseCase = getBeersListByCoroutineUseCase,
                savedStateHandle = savedStateHandle
            )
            viewModel.productsListByCoroutine.asLiveData().observeForever(productsListObserver)

            // Then
            advanceTimeBy(10)
            coVerify(exactly = 1) {
                getBeersListByCoroutineUseCase.invoke(
                    GetBeersListByCoroutineParams(anyString())
                )
            }
            verify { productsListObserver.onChanged(matchNullable { it != null }) }
        }

}