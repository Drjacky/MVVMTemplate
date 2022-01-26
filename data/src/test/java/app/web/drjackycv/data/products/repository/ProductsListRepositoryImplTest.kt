package app.web.drjackycv.data.products.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.PagingSource
import app.web.drjackycv.data.products.datasource.ProductsPagingSource
import app.web.drjackycv.data.products.datasource.ProductsPagingSourceByCoroutine
import app.web.drjackycv.data.products.factory.ProductResponseFactory
import app.web.drjackycv.data.products.remote.ProductsApi
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProductsListRepositoryImplTest {

    private lateinit var productsListRepositoryImpl: ProductsListRepositoryImpl

    @MockK
    lateinit var loadParams: PagingSource.LoadParams<Int>

    @MockK
    lateinit var pagingSource: ProductsPagingSource

    @MockK
    lateinit var pagingSourceByCoroutine: ProductsPagingSourceByCoroutine

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

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
    fun `test getBeersListByCoroutine() gives list of products`() =
        runTest {
            // Given
            every { loadParams.key } returns 10
            val givenProducts = ProductResponseFactory.createProducts(3)
            val productsApi = mockk<ProductsApi> {
                coEvery { getBeersListByCoroutine() } returns givenProducts
            }
            productsListRepositoryImpl =
                ProductsListRepositoryImpl(pagingSource, pagingSourceByCoroutine)
            val pagingSource = PagingData.from(givenProducts)

            // When
            coEvery { productsApi.getBeersListByCoroutine() }
                .returns(givenProducts)

            // Invoke
            val productsListRepositoryResponseFlow =
                productsListRepositoryImpl.getBeersListByCoroutine("")

            // Then
            assertThat(productsListRepositoryResponseFlow, notNullValue())
            assertThat(productsListRepositoryResponseFlow, instanceOf(Flow::class.java))

            /*val productsList = (productsListRepositoryResponseFlow as PagingData<RecyclerItem>)
            MatcherAssert.assertThat(productsList, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(productsList.size, CoreMatchers.`is`(givenProducts.size))*/

            //coVerify(exactly = 1) { productsApi.getBeersListByCoroutine() }
            confirmVerified(productsApi)
        }

}