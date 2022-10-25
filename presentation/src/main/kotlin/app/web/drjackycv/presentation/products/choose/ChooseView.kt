package app.web.drjackycv.presentation.products.choose

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import app.web.drjackycv.presentation.base.view.ChooseArrowAnimation
import coil.annotation.ExperimentalCoilApi
import java.io.Serializable

@ExperimentalLifecycleComposeApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Composable
fun ChooseRoute(
    themeFAB: @Composable () -> Unit,
    navigateToProductsList: (ChoosePathType) -> Unit,
) {
    ChooseView(
        themeFAB = themeFAB,
        navigateToProductsList = navigateToProductsList,
    )
}

@Composable
fun ChooseView(
    themeFAB: @Composable () -> Unit,
    navigateToProductsList: (ChoosePathType) -> Unit
) {
    Scaffold(
        floatingActionButton = themeFAB, content = { padding ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                val (chooseAnimation, rxButton, coroutineButton) = createRefs()

                SetupAndRunChooseArrowAnimation(
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
                    onClick = { navigateToProductsList(ChoosePathType.RX) }
                ) {
                    Text(
                        text = "RxJava",
                        modifier = Modifier.padding(4.dp),
                        style = TextStyle(fontSize = 11.sp)
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
                    onClick = { navigateToProductsList(ChoosePathType.COROUTINE) }
                ) {
                    Text(
                        text = "Coroutines",
                        modifier = Modifier.padding(4.dp),
                        style = TextStyle(fontSize = 11.sp)
                    )
                }
            }
        })
}

@Composable
fun SetupAndRunChooseArrowAnimation(
    modifier: Modifier = Modifier,
) {
    var circleState by remember { mutableStateOf(CirclePosition.Start) }

    ChooseArrowAnimation(
        modifier = modifier,
        circleState = circleState
    )
    LaunchedEffect(Unit) {
        circleState = when (circleState) {
            CirclePosition.Start -> CirclePosition.Finish
            CirclePosition.Finish -> CirclePosition.Start
        }
    }
}

@Composable //TODO
fun PathButton(name: String/*, selectedPath: (ChoosePathType) -> Unit*/) {
    Button(
        onClick = {

        }
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(4.dp),
            style = TextStyle(fontSize = 11.sp)
        )
    }
}

enum class CirclePosition {
    Start, Finish
}

enum class ChoosePathType : Serializable {
    COROUTINE,
    RX
}

@Preview
@Composable
private fun ChooseViewPreview() {
    ChooseView(themeFAB = {}) { ChoosePathType.COROUTINE }
}