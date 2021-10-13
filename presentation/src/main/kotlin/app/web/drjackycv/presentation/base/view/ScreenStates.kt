package app.web.drjackycv.presentation.base.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.web.drjackycv.presentation.R

@Composable
fun ErrorItemView(
    message: String?,
    onClickRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (text, button) = createRefs()

        Text(
            text = message ?: stringResource(id = R.string.error_unknown),
            maxLines = 2,
            style = MaterialTheme.typography.body1,
            color = Color.Red,
            modifier = modifier
                .constrainAs(text) {
                    start.linkTo(parent.start, margin = 10.dp)
                    end.linkTo(button.start, margin = 5.dp)
                    top.linkTo(button.top)
                    bottom.linkTo(button.bottom)
                }
        )
        OutlinedButton(
            onClick = onClickRetry,
            modifier = modifier
                .constrainAs(button) {
                    start.linkTo(text.end, margin = 5.dp)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Text(text = "Try again")
        }
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

@Preview
@Composable
fun ErrorItemViewPreview() {
    ErrorItemView(message = null, onClickRetry = {})
}

@Preview
@Composable
fun LoadingItemViewPreview() {
    LoadingItemView()
}