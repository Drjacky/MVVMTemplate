package app.web.drjackycv.presentation.products.choose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import java.io.Serializable

@Composable
fun ChooseView(
    themeFAB: @Composable () -> Unit,
    onSelectedPath: (selectedPathType: ChoosePathType) -> Unit
) {
    Scaffold(
        floatingActionButton = themeFAB
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (rxButton, coroutineButton) = createRefs()

            Button(
                modifier = Modifier
                    .requiredWidth(100.dp)
                    .constrainAs(rxButton) {
                        start.linkTo(parent.start, margin = 20.dp)
                        end.linkTo(coroutineButton.start, margin = 10.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                onClick = { onSelectedPath(ChoosePathType.RX) }
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
                onClick = { onSelectedPath(ChoosePathType.COROUTINE) }
            ) {
                Text(
                    text = "Coroutines",
                    modifier = Modifier.padding(4.dp),
                    style = TextStyle(fontSize = 11.sp)
                )
            }
        }
    }
}

@Composable
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

enum class ChoosePathType : Serializable {
    COROUTINE,
    RX
}

@Preview
@Composable
fun ChooseViewPreview() {
    ChooseView(themeFAB = {}) { ChoosePathType.COROUTINE }
}