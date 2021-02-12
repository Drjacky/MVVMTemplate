package app.web.drjackycv.presentation.products.productdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.web.drjackycv.presentation.base.compose.BaseTheme
import app.web.drjackycv.presentation.base.compose.transparent

@Composable
fun ProductDetailComposable(content: @Composable () -> Unit) {
    BaseTheme {
        Surface(color = transparent) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(name: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        ProductDetailName(name = name)
    }
}

@Composable
fun ProductDetailName(name: String) {
    Text(
        text = name,
        modifier = Modifier.padding(16.dp),
        style = TextStyle(fontSize = 17.sp),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProductDetailComposable {
        MyScreenContent("Buzz")
    }
}