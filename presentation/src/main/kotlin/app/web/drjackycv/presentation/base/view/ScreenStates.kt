package app.web.drjackycv.presentation.base.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.web.drjackycv.presentation.R
import com.airbnb.lottie.compose.*

@Composable
fun ErrorListView(
    message: String?,
    onClickRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (image, text, button) = createRefs()
        val guide = createGuidelineFromTop(0.5f)

        ErrorRowView(
            modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(guide)
                })
        Text(
            text = message ?: stringResource(id = R.string.error_unknown),
            maxLines = 2,
            style = MaterialTheme.typography.body1,
            color = Color.Red,
            modifier = Modifier
                .constrainAs(text) {
                    start.linkTo(parent.start, margin = 10.dp)
                    end.linkTo(button.start, margin = 5.dp)
                    top.linkTo(button.top)
                    bottom.linkTo(button.bottom)
                }
        )
        OutlinedButton(
            onClick = onClickRetry,
            modifier = Modifier
                .constrainAs(button) {
                    start.linkTo(text.end, margin = 5.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    top.linkTo(guide)
                }
        ) {
            Text(text = "Try again")
        }
    }
}

@Composable
fun ErrorItemView(
    message: String?,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (image, text) = createRefs()
        val guide = createGuidelineFromTop(0.5f)

        ErrorRowView(
            modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(guide)
                })
        Text(
            text = message ?: stringResource(id = R.string.error_unknown),
            maxLines = 2,
            style = MaterialTheme.typography.body1,
            color = Color.Red,
            modifier = Modifier
                .constrainAs(text) {
                    start.linkTo(parent.start, margin = 10.dp)
                    end.linkTo(parent.end, margin = 5.dp)
                    top.linkTo(guide)
                }
        )
    }
}

@Composable
fun LoadingItemView(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
    )
}

@Composable
fun ErrorRowView(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_image))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { progress },
    )
}

@Preview
@Composable
private fun ErrorListViewPreview() {
    ErrorListView(message = null, onClickRetry = {})
}

@Preview
@Composable
private fun ErrorItemViewPreview() {
    ErrorItemView(message = null)
}

@Preview
@Composable
private fun LoadingItemViewPreview() {
    LoadingItemView()
}

@Preview
@Composable
private fun ErrorRowViewPreview() {
    ErrorRowView()
}