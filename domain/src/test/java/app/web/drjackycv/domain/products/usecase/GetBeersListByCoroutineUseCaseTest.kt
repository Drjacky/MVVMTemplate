package app.web.drjackycv.domain.products.usecase

import androidx.paging.PagingData
import app.web.drjackycv.domain.products.factory.ProductFactory
import app.web.drjackycv.domain.products.repository.ProductsListRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
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

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test invoking GetBeersListByCoroutineUseCase gives list of products`() = runBlocking {
        // Given
        val usecase = GetBeersListByCoroutineUseCase(productsListRepository)
        val givenProducts = ProductFactory.createProducts(3)

        // When
        coEvery { productsListRepository.getBeersListByCoroutine(anyString()) }
            .returns(flowOf(PagingData.from(givenProducts)))

        // Invoke
        val productsListFlow = usecase(GetBeersListByCoroutineParams(""))

        // Then
        MatcherAssert.assertThat(productsListFlow, CoreMatchers.notNullValue())

        val productsListDataState = productsListFlow.first()
        MatcherAssert.assertThat(productsListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            productsListDataState,
            CoreMatchers.instanceOf(PagingData::class.java)
        )

        val productsList = (productsListDataState as PagingData)
        MatcherAssert.assertThat(productsList, CoreMatchers.notNullValue())
        //MatcherAssert.assertThat(productsList.size, `is`(givenProducts.size))
    }
}