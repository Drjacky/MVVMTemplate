package app.web.drjackycv.presentation.base.view

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.web.drjackycv.presentation.base.theme.purple600
import app.web.drjackycv.presentation.base.theme.red200
import app.web.drjackycv.presentation.extension.lineTo
import app.web.drjackycv.presentation.extension.percentOf
import app.web.drjackycv.presentation.products.choose.CirclePosition


@Suppress("MagicNumber")
@Composable
fun ChooseArrowAnimation(
    circleState: CirclePosition,
    modifier: Modifier = Modifier,
) {
    val offsetAnimation: Dp by animateDpAsState(
        label = "offsetAnimation",
        targetValue = if (circleState == CirclePosition.Start) 0.dp else 100.dp,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
    )
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val angle by infiniteTransition.animateFloat(
        label = "angleAnimation",
        initialValue = 0F,
        targetValue = 180F,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .requiredWidth(200.dp)
            .requiredHeight(100.dp)
    ) {

        Canvas(
            modifier = Modifier
                .requiredWidth(200.dp)
                .requiredHeight(100.dp)
                .padding(16.dp)
        ) {
            val rect = Rect(Offset.Zero, size)
            val percent = 65.percentOf(rect.minDimension)
            val ovalPath = Path().apply {
                with(rect) {
                    lineTo(topRight)
                    lineTo(bottomRight)
                    lineTo(bottomLeft)
                    lineTo(topLeft)
                    close()
                }
            }

            drawIntoCanvas { canvas ->
                canvas.drawOutline(
                    outline = Outline.Generic(ovalPath),
                    paint = Paint().apply {
                        color = purple600
                        strokeWidth = 10f
                        pathEffect = PathEffect.cornerPathEffect(percent)
                        style = PaintingStyle.Stroke
                    }
                )
            }
        }

        Canvas(
            modifier = Modifier
                .requiredWidth(96.dp)
                .requiredHeight(96.dp)
                .padding(20.dp)
                .absoluteOffset(x = offsetAnimation, y = 2.dp)
                .rotate(angle)
        ) {
            drawCircle(red200)
            drawLine(
                color = purple600,
                start = Offset(82f, 105f),
                end = Offset(54f, 77f),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
            drawLine(
                color = purple600,
                start = Offset(54f, 77f),
                end = Offset(82f, 49f),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
        }
    }

}

@Preview
@Composable
private fun ChooseArrowAnimationPreview() {
    ChooseArrowAnimation(
        modifier = Modifier,
        circleState = CirclePosition.Start
    )
}