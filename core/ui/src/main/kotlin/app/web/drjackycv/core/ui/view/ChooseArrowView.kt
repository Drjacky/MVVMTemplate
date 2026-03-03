@file:Suppress("MatchingDeclarationName")

package app.web.drjackycv.core.ui.view

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.web.drjackycv.core.ui.R
import app.web.drjackycv.core.ui.theme.arrowCircle
import app.web.drjackycv.core.ui.theme.arrowIcon
import app.web.drjackycv.core.ui.theme.purple600

enum class CirclePosition {
    Start, Finish
}

@Suppress("MagicNumber", "LongMethod")
@Composable
fun ChooseArrowAnimation(
    modifier: Modifier = Modifier,
) {
    var circleState by remember { mutableStateOf(CirclePosition.Start) }

    LaunchedEffect(Unit) {
        circleState = when (circleState) {
            CirclePosition.Start -> CirclePosition.Finish
            CirclePosition.Finish -> CirclePosition.Start
        }
    }

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

    val chooseDesc = stringResource(R.string.content_description_choose_animation)
    Box(
        modifier = modifier
            .requiredWidth(200.dp)
            .requiredHeight(100.dp)
            .semantics { contentDescription = chooseDesc },
    ) {
        Canvas(
            modifier = Modifier
                .requiredWidth(200.dp)
                .requiredHeight(100.dp)
                .size(200.dp, 100.dp)
                .padding(16.dp)
        ) {
            val canvasHeight = size.height
            val circleRadius = 30.dp.toPx()
            val arrowLength = 20.dp.toPx()
            val arrowThickness = 3.dp.toPx()
            val padding = 4.dp.toPx() // Add padding to prevent overlap

            // Draw the rounded rectangle
            drawRoundRect(
                color = purple600,
                size = size,
                cornerRadius = CornerRadius(50.dp.toPx(), 50.dp.toPx()),
                style = Stroke(width = 4.dp.toPx())
            )

            // Draw animated red dots on the stroke
            val dotSizeW = 12.dp.toPx()
            val dotSizeH = 4.dp.toPx()

            // Calculate dot positions based on the circle's animation
            val dot1X = padding + circleRadius + offsetAnimation.toPx()
            val dot2X = padding + circleRadius + offsetAnimation.toPx()
            val dot1Y = (canvasHeight / 2) - padding - circleRadius
            val dot2Y = canvasHeight

            // Draw first dot (square)
            drawRect(
                color = arrowCircle,
                topLeft = androidx.compose.ui.geometry.Offset(dot1X - dotSizeW / 2, dot1Y - dotSizeH / 2),
                size = androidx.compose.ui.geometry.Size(dotSizeW, dotSizeH)
            )

            // Draw second dot (square)
            drawRect(
                color = arrowCircle,
                topLeft = androidx.compose.ui.geometry.Offset(dot2X - dotSizeW / 2, dot2Y - dotSizeH / 2),
                size = androidx.compose.ui.geometry.Size(dotSizeW, dotSizeH)
            )

            // Calculate the circle's position with padding
            val circleX = padding + circleRadius + offsetAnimation.toPx()
            val circleY = canvasHeight / 2

            // Draw the red circle
            translate(left = circleX - circleRadius, top = circleY - circleRadius) {
                rotate(
                    angle,
                    pivot = androidx.compose.ui.geometry.Offset(circleRadius, circleRadius)
                ) {
                    drawCircle(
                        color = arrowCircle,
                        radius = circleRadius,
                        center = androidx.compose.ui.geometry.Offset(circleRadius, circleRadius)
                    )

                    // Draw the ">" arrow inside the circle
                    val arrowPath = Path().apply {
                        moveTo(circleRadius - arrowLength / 4, circleRadius - arrowLength / 2)
                        lineTo(circleRadius + arrowLength / 4, circleRadius)
                        lineTo(circleRadius - arrowLength / 4, circleRadius + arrowLength / 2)
                    }

                    drawPath(
                        path = arrowPath,
                        color = arrowIcon,
                        style = Stroke(width = arrowThickness)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ChooseArrowAnimationPreview() {
    ChooseArrowAnimation(
        modifier = Modifier,
    )
}
