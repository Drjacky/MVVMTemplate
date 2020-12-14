package app.web.drjackycv.presentation.products.productdetail

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import app.web.drjackycv.presentation.base.compose.*
import androidx.compose.ui.text.style.TextOverflow

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
        style = TextStyle(fontSize = TextUnit.Sp(17)),
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