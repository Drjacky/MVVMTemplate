package app.web.drjackycv.presentation.products.productdetail

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.web.drjackycv.presentation.products.entity.BeerUI
import coil.annotation.ExperimentalCoilApi

@ExperimentalCoilApi
@ExperimentalAnimationGraphicsApi
@Composable
fun ProductDetailView(
    themeFAB: @Composable () -> Unit,
    popBackStack: () -> Unit,
    product: BeerUI
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = product.name)
                },
                navigationIcon = {
                    IconButton(onClick = { popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButton = themeFAB
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = product.description,
                modifier = Modifier.padding(16.dp),
                style = TextStyle(fontSize = 17.sp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }

}

@ExperimentalCoilApi
@ExperimentalAnimationGraphicsApi
@Preview(showBackground = true)
@Composable
fun ProductDetailViewPreview() {
    ProductDetailView(
        themeFAB = {},
        popBackStack = {},
        product = BeerUI(
            id = 1,
            name = "name",
            tagline = "tagline",
            description = "description",
            imageUrl = "https://images.punkapi.com/v2/5.png",
            abv = 4.9
        )
    )
}