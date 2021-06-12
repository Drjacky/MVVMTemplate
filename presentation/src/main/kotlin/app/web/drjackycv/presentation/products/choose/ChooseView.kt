package app.web.drjackycv.presentation.products.choose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.Serializable

@Composable
fun ChooseView(
    selectedPath: (selectedPathType: ChoosePathType2) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Greeting(
                name = "Rx"
            )
            Greeting(
                name = "Coroutines"
            )
        }
    }

}

@Composable
fun Greeting(name: String/*, selectedPath: (ChoosePathType2) -> Unit*/) {
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

enum class ChoosePathType2 : Serializable {
    COROUTINE,
    RX
}