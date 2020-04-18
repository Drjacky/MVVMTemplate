package app.web.drjackycv.data.products.datasource

import app.web.drjackycv.data.extension.assertGeneralsSuccess
import app.web.drjackycv.data.extension.getSingleResultSuccess
import app.web.drjackycv.data.products.entity.ProductsResponseFactory.Companion.providesClustersResponse
import app.web.drjackycv.data.products.entity.ProductsResponseFactory.Companion.providesProductDetailResponse
import app.web.drjackycv.data.products.remote.ProductsApi
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
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
    fun `getClusters should return a list of clusters`() {
        whenever(productsApi.getClusters())
            .doReturn(getSingleResultSuccess(providesClustersResponse()))

        val testObserver = productsRemoteDataSource.getClusters().test()

        testObserver.assertGeneralsSuccess {
            it.clusters.isNotEmpty()
        }
    }

    @Test
    fun `getProductDetail should return a product detail`() {
        val productId = 309396011
        whenever(productsApi.getProductDetail(anyInt()))
            .doReturn(getSingleResultSuccess(providesProductDetailResponse()))

        val testObserver = productsRemoteDataSource.getProductDetail(productId).test()

        testObserver.assertGeneralsSuccess {
            it.id == productId
        }
    }

}