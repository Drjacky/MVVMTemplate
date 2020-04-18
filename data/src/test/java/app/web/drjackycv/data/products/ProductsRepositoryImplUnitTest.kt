package app.web.drjackycv.data.products

import app.web.drjackycv.data.extension.assertGeneralsError
import app.web.drjackycv.data.extension.assertGeneralsSuccess
import app.web.drjackycv.data.extension.getSingleError
import app.web.drjackycv.data.extension.getSingleResultSuccess
import app.web.drjackycv.data.products.datasource.ProductsRemoteDataSource
import app.web.drjackycv.data.products.entity.ProductsFactory.Companion.providesClusters
import app.web.drjackycv.data.products.entity.ProductsFactory.Companion.providesProductDetail
import app.web.drjackycv.data.products.repository.ProductsRepositoryImpl
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductsRepositoryImplUnitTest {

    private lateinit var productsRepositoryImpl: ProductsRepositoryImpl

    @Mock
    private lateinit var productsRemoteDataSource: ProductsRemoteDataSource

    @Before
    fun setUp() {
        productsRepositoryImpl = ProductsRepositoryImpl(productsRemoteDataSource)
    }

    @Test
    fun `getClusters should return a list of clusters`() {
        whenever(productsRemoteDataSource.getClusters())
            .doReturn(getSingleResultSuccess(providesClusters()))

        val testObserver = productsRemoteDataSource.getClusters().test()

        testObserver.assertGeneralsSuccess {
            it.clusters.isNotEmpty()
        }
    }

    @Test
    fun `getClusters should not return a list of clusters in case of an error`() {
        whenever(productsRemoteDataSource.getClusters())
            .doReturn(getSingleError())

        val testObserver = productsRemoteDataSource.getClusters().test()

        testObserver.assertGeneralsError()
    }

    @Test
    fun `getProductDetail should return a product detail`() {
        val productId = 309396011
        whenever(productsRemoteDataSource.getProductDetail(anyInt()))
            .doReturn(getSingleResultSuccess(providesProductDetail()))

        val testObserver = productsRemoteDataSource.getProductDetail(productId).test()

        testObserver.assertGeneralsSuccess {
            it.id == productId
        }
    }

}