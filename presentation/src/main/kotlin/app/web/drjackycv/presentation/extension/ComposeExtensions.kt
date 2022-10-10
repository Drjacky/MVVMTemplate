package app.web.drjackycv.presentation.extension

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path

fun Int.percentOf(target: Float) = (this.toFloat() / 100) * target
fun Path.moveTo(offset: Offset) = moveTo(offset.x, offset.y)
fun Path.lineTo(offset: Offset) = lineTo(offset.x, offset.y)