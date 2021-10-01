package app.web.drjackycv.presentation.products.productlist

//import androidx.compose.ui.res.animatedVectorResource
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.products.entity.BeerUI
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter

@ExperimentalAnimationGraphicsApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun ProductRowView(product: BeerUI, selectedProduct: (network: BeerUI) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { selectedProduct(product) })
            .padding(4.dp),
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp
    ) {
        Row() {
            Surface(
                modifier = Modifier
                    .size(72.dp)
                    .padding(4.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            ) {
                val painter = rememberImagePainter(
                    data = product.imageUrl,
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.ic_cloud_download)
                    }
                )
                Image(
                    painter = painter,
                    modifier = Modifier.size(72.dp),
                    contentDescription = product.name,
                )

                when (painter.state) {
                    is ImagePainter.State.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.padding(8.dp))
                    }
                    is ImagePainter.State.Error -> {
//                        val failedImage = animatedVectorResource(id = R.drawable.ic_cloud_download)
//                        var atEnd by remember { mutableStateOf(false) }

                        Image(
//                            painter = failedImage.painterFor(atEnd = atEnd),
                            painter = painterResource(id = R.drawable.ic_cloud_download),
                            modifier = Modifier
                                .size(72.dp)
                                .clickable {
//                                    atEnd = atEnd.not()
                                },
                            contentDescription = product.name,
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
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
    }
    Spacer(modifier = Modifier.height(2.dp))
}

@ExperimentalAnimationGraphicsApi
@ExperimentalCoilApi
@Preview
@Composable
fun ProductRowViewPreview() {
    ProductRowView(
        product = BeerUI(
            id = 1,
            name = "name",
            tagline = "tagline",
            description = "description",
            imageUrl = "https://images.punkapi.com/v2/5.png",
            abv = 4.9
        )
    ) {}
}
