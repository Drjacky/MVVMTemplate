package app.web.drjackycv.core.ui.extension

import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material3.fade
import com.google.accompanist.placeholder.material3.placeholder
import com.google.accompanist.placeholder.material3.shimmer

fun Modifier.clickableOnce(
    onClick: () -> Unit
) = composed {
    val clickState = remember { mutableStateOf(false) }

    Modifier.clickable(
        onClick = {
            if (clickState.value.not()) {
                clickState.value = true
                onClick()
            }
        }
    )
}

fun Modifier.fade(visible: Boolean) = composed {
    Modifier.placeholder(
        highlight = PlaceholderHighlight.fade(),
        visible = visible,
    )
}

fun Modifier.shimmer(visible: Boolean) = composed {
    Modifier.placeholder(
        highlight = PlaceholderHighlight.shimmer(),
        visible = visible,
    )
}
