package app.web.drjackycv.data.products.datasource

import app.web.drjackycv.data.products.remote.ProductsApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductsRemoteDataSourceUnitTest {

    private lateinit var productsRemoteDataSource: ProductsRemoteDataSource

    @Mock
    private lateinit var productsApi: ProductsApi

    @Before
    fun setUp() {
        productsRemoteDataSource = ProductsRemoteDataSource(productsApi)
    }

    @Test
    fun `getBeers should return a list of beers`() {
        /*whenever(productsApi.getBeers())
            .doReturn(getSingleResultSuccess(providesClustersResponse()))

        val testObserver = productsRemoteDataSource.getBeers().test()

        testObserver.assertGeneralsSuccess {
            it.clusters.isNotEmpty()
        }*/
    }

}