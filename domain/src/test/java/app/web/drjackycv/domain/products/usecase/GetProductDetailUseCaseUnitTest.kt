package app.web.drjackycv.domain.products.usecase

import app.web.drjackycv.domain.extension.*
import app.web.drjackycv.domain.products.entity.ProductsFactory.Companion.providesProductDetail
import app.web.drjackycv.domain.products.repository.ProductsRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetProductDetailUseCaseUnitTest {

    private lateinit var getProductDetailUseCase: GetProductDetailUseCase

    @Mock
    private lateinit var productsRepository: ProductsRepository

    @Before
    fun setUp() {
        getProductDetailUseCase = GetProductDetailUseCase(productsRepository)
    }

    @Test
    fun `invoke should return product detail`() {
        val productId = 309396011
        whenever(productsRepository.getProductDetail(anyInt()))
            .doReturn(getSingleSuccess(providesProductDetail()))

        val testObserver =
            getProductDetailUseCase(GetProductDetailParams(productId = productId)).testAwait()

        testObserver.assertGeneralsSuccess {
            it.id == productId
        }
    }

    @Test
    fun `invoke should not return product detail in case of an error`() {
        val productId = 309396011
        whenever(productsRepository.getProductDetail(anyInt()))
            .doReturn(getSingleError())

        val testObserver =
            getProductDetailUseCase(GetProductDetailParams(productId = productId)).testAwait()

        testObserver.assertGeneralsError()
    }

}