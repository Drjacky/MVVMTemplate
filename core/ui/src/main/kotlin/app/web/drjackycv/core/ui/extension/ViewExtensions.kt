package app.web.drjackycv.core.ui.extension

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.web.drjackycv.core.ui.theme.ThemeState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material3.fade
import com.google.accompanist.placeholder.material3.placeholder
import com.google.accompanist.placeholder.material3.shimmer

fun Modifier.frostedGlass(
    shape: Shape = RoundedCornerShape(16.dp),
    borderWidth: Dp = 0.5.dp,
): Modifier {
    val isDark = ThemeState.darkModeState.value
    val bgColor = if (isDark) {
        Color.White.copy(alpha = 0.10f)
    } else {
        Color.White.copy(alpha = 0.55f)
    }
    val borderColor = Color.White.copy(alpha = if (isDark) 0.15f else 0.4f)

    return this
        .clip(shape)
        .background(bgColor)
        .border(borderWidth, borderColor, shape)
}

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
