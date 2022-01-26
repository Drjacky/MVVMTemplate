package app.web.drjackycv.domain.products.usecase

import androidx.paging.PagingData
import app.web.drjackycv.domain.products.factory.ProductFactory
import app.web.drjackycv.domain.products.repository.ProductsListRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString

@RunWith(JUnit4::class)
class GetBeersListByCoroutineUseCaseTest {

    @MockK
    lateinit var productsListRepository: ProductsListRepository

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
    fun `test invoking GetBeersListByCoroutineUseCase gives list of products`() =
        runTest {
            // Given
            val usecase = GetBeersListByCoroutineUseCase(productsListRepository)
            val givenProducts = ProductFactory.createProducts(3)
            val expectedProducts = PagingData.from(givenProducts)

            // When
            coEvery { productsListRepository.getBeersListByCoroutine(anyString()) }
                .returns(flowOf(expectedProducts))

            // Invoke
            val productsListFlow = usecase(GetBeersListByCoroutineParams(""))

            // Then
            assertThat(productsListFlow, notNullValue())

            val productsListDataState = productsListFlow.first()
            assertThat(productsListDataState, notNullValue())
            assertThat(productsListDataState, instanceOf(PagingData::class.java))
            assertThat(productsListDataState, equalTo(expectedProducts))
        }
}