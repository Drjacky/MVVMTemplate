package app.web.drjackycv.presentation.products.productlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.animatedVectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.products.entity.BeerUI
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

@ExperimentalComposeUiApi
@Composable
fun ProductRowView(product: BeerUI, selectedProduct: (network: BeerUI) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { selectedProduct(product) })
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            modifier = Modifier.size(72.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            val painter = rememberCoilPainter(
                request = product.imageUrl,
                fadeIn = true
            )
            Image(
                painter = painter,
                modifier = Modifier.size(72.dp),
                contentDescription = product.name,
            )

            when (painter.loadState) {
                is ImageLoadState.Loading -> {
                    CircularProgressIndicator(/*Modifier.align(Alignment.Center)*/)
                }
                is ImageLoadState.Error -> {
//                    val failedImage = animatedVectorResource(id = R.drawable.ic_cloud_download)
                    //val atEnd by remember { mutableStateOf(false) }

                    Image(
//                        painter = failedImage.painterFor(atEnd = false),
                        painter = painterResource(id = R.drawable.ic_cloud_download),
                        modifier = Modifier.size(72.dp),
                        contentDescription = product.name,
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(start = 4.dp, end = 4.dp)) {

            Text(
                text = product.name,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Normal
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = product.id.toString(),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
    Divider()
}
