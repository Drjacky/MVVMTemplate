package app.web.drjackycv.feature.products.choose

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.web.drjackycv.feature.products.R
import app.web.drjackycv.core.ui.view.ChooseArrowAnimation
import coil.annotation.ExperimentalCoilApi
import java.io.Serializable


@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Composable
fun ChooseRoute(
    themeFAB: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigateToProductsList: (ChoosePathType) -> Unit,
) {
    ChooseView(
        themeFAB = themeFAB,
        modifier = modifier,
        navigateToProductsList = navigateToProductsList,
    )
}

@Composable
fun ChooseView(
    themeFAB: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigateToProductsList: (ChoosePathType) -> Unit,
) {
    Scaffold(
        floatingActionButton = themeFAB,
        content = { padding ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                val (chooseAnimation, rxButton, coroutineButton) = createRefs()

                ChooseArrowAnimation(
                    modifier = Modifier
                        .constrainAs(chooseAnimation) {
                            start.linkTo(parent.start, margin = 20.dp)
                            end.linkTo(parent.end, margin = 20.dp)
                            bottom.linkTo(rxButton.top, margin = 20.dp)
                        }
                )
                Button(
                    modifier = Modifier
                        .requiredWidth(100.dp)
                        .constrainAs(rxButton) {
                            start.linkTo(parent.start, margin = 20.dp)
                            end.linkTo(coroutineButton.start, margin = 10.dp)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    onClick = { navigateToProductsList(ChoosePathType.RX) },
                ) {
                    Text(
                        text = stringResource(R.string.rxjava),
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                Button(
                    modifier = Modifier
                        .requiredWidth(100.dp)
                        .constrainAs(coroutineButton) {
                            start.linkTo(rxButton.end, margin = 10.dp)
                            end.linkTo(parent.end, margin = 20.dp)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    onClick = { navigateToProductsList(ChoosePathType.COROUTINE) },
                ) {
                    Text(
                        text = stringResource(R.string.coroutines),
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
        },
        modifier = modifier,
    )
}

enum class ChoosePathType : Serializable {
    COROUTINE,
    RX,
}

@Preview
@Composable
private fun ChooseViewPreview() {
    ChooseView(themeFAB = {}, modifier = Modifier) { ChoosePathType.COROUTINE }
}
