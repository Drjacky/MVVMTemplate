package app.web.drjackycv.feature.products.navigation

import app.web.drjackycv.feature.products.choose.ChoosePathType
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class FakeProductsNavigatorTest {

    private lateinit var navigator: FakeProductsNavigator

    @Before
    fun setup() {
        navigator = FakeProductsNavigator()
    }

    @Test
    fun `navigateToProductsList tracks event with path type`() {
        navigator.navigateToProductsList(ChoosePathType.COROUTINE)

        assertThat(navigator.navigationEvents).hasSize(1)
        assertThat(navigator.navigationEvents[0]).isEqualTo("navigateToProductsList:COROUTINE")
    }

    @Test
    fun `navigateToProduct tracks event with product id and path type`() {
        navigator.navigateToProduct("42", ChoosePathType.COROUTINE)

        assertThat(navigator.navigationEvents).hasSize(1)
        assertThat(navigator.navigationEvents[0]).isEqualTo("navigateToProduct:42:COROUTINE")
    }

    @Test
    fun `navigateBack tracks event`() {
        navigator.navigateBack()

        assertThat(navigator.navigationEvents).hasSize(1)
        assertThat(navigator.navigationEvents[0]).isEqualTo("navigateBack")
    }

    @Test
    fun `multiple navigation events are tracked in order`() {
        navigator.navigateToProductsList(ChoosePathType.RX)
        navigator.navigateToProduct("1", ChoosePathType.RX)
        navigator.navigateToProduct("2", ChoosePathType.RX)
        navigator.navigateBack()

        assertThat(navigator.navigationEvents).hasSize(4)
        assertThat(navigator.navigationEvents[0]).isEqualTo("navigateToProductsList:RX")
        assertThat(navigator.navigationEvents[1]).isEqualTo("navigateToProduct:1:RX")
        assertThat(navigator.navigationEvents[2]).isEqualTo("navigateToProduct:2:RX")
        assertThat(navigator.navigationEvents[3]).isEqualTo("navigateBack")
    }

    @Test
    fun `clearEvents removes all tracked events`() {
        navigator.navigateToProductsList(ChoosePathType.COROUTINE)
        navigator.navigateToProduct("1", ChoosePathType.COROUTINE)

        assertThat(navigator.navigationEvents).isNotEmpty()

        navigator.clearEvents()

        assertThat(navigator.navigationEvents).isEmpty()
    }

    @Test
    fun `getLastEvent returns most recent navigation`() {
        navigator.navigateToProductsList(ChoosePathType.COROUTINE)
        navigator.navigateToProduct("42", ChoosePathType.COROUTINE)

        assertThat(navigator.getLastEvent()).isEqualTo("navigateToProduct:42:COROUTINE")
    }

    @Test
    fun `getLastEvent returns null when no events`() {
        assertThat(navigator.getLastEvent()).isNull()
    }
}
