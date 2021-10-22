package app.web.drjackycv.presentation.extension

import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

internal fun Modifier.clickableOnce(
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

internal fun Modifier.fade(visible: Boolean) = composed {
    Modifier.placeholder(
        highlight = PlaceholderHighlight.fade(),
        visible = visible,
    )
}

internal fun Modifier.shimmer(visible: Boolean) = composed {
    Modifier.placeholder(
        highlight = PlaceholderHighlight.shimmer(),
        visible = visible,
    )
}
