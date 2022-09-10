package app.web.drjackycv.presentation.extension

import androidx.annotation.DrawableRes
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.painter.Painter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
internal fun rememberInfiniteAnimatedVectorPainter(@DrawableRes id: Int, duration: Long): Painter {
    val image = AnimatedImageVector.animatedVectorResource(id)
    var atEnd by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(image) {
        launch {
            while (true) {
                atEnd = !atEnd
                delay(duration)
            }
        }
    }
    return rememberAnimatedVectorPainter(animatedImageVector = image, atEnd = atEnd)
}

