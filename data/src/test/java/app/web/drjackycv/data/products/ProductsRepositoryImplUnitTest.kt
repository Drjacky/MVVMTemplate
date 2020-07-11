package app.web.drjackycv.data.products

import app.web.drjackycv.data.products.datasource.ProductsRemoteDataSource
import app.web.drjackycv.data.products.repository.ProductsRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductsRepositoryImplUnitTest {

    private lateinit var productsRepositoryImpl: ProductsRepositoryImpl

    @Mock
    private lateinit var productsRemoteDataSource: ProductsRemoteDataSource

    @Before
    fun setUp() {
        //productsRepositoryImpl = ProductsRepositoryImpl(productsRemoteDataSource)
    }

    @Test
    fun `getBeersById should return a list of beers`() {
        /*whenever(productsRemoteDataSource.getBeersById())
            .doReturn(getSingleResultSuccess(providesClusters()))

        val testObserver = productsRemoteDataSource.getBeersById().test()

        testObserver.assertGeneralsSuccess {
            it.clusters.isNotEmpty()
        }*/
    }

    @Test
    fun `getBeersById should not return a list of beers in case of an error`() {
        /*whenever(productsRemoteDataSource.getBeersById())
            .doReturn(getSingleError())

        val testObserver = productsRemoteDataSource.getBeersById().test()

        testObserver.assertGeneralsError()*/
    }

}