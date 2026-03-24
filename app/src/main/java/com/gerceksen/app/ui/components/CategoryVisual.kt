package com.gerceksen.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gerceksen.app.model.CategoryIcon
import com.gerceksen.app.ui.theme.AccentAmber
import com.gerceksen.app.ui.theme.AccentTeal
import com.gerceksen.app.ui.theme.AccentViolet
import com.gerceksen.app.ui.theme.CoralPrimary

@Composable
fun CategoryVisual(
    icon: CategoryIcon,
    modifier: Modifier = Modifier,
    size: androidx.compose.ui.unit.Dp = 56.dp,
) {
    val (brush, emoji) = when (icon) {
        CategoryIcon.Social -> Pair(
            Brush.linearGradient(listOf(CoralPrimary.copy(alpha = 0.85f), AccentViolet.copy(alpha = 0.7f))),
            "👥",
        )
        CategoryIcon.Character -> Pair(
            Brush.linearGradient(listOf(AccentTeal.copy(alpha = 0.85f), AccentAmber.copy(alpha = 0.65f))),
            "🎭",
        )
        CategoryIcon.Relations -> Pair(
            Brush.linearGradient(listOf(AccentViolet.copy(alpha = 0.8f), CoralPrimary.copy(alpha = 0.65f))),
            "💬",
        )
        CategoryIcon.Truth -> Pair(
            Brush.linearGradient(listOf(AccentAmber.copy(alpha = 0.9f), Color(0xFFE85D4C).copy(alpha = 0.7f))),
            "🔥",
        )
        CategoryIcon.Strength -> Pair(
            Brush.linearGradient(listOf(CoralPrimary.copy(alpha = 0.75f), AccentTeal.copy(alpha = 0.8f))),
            "✨",
        )
    }
    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(18.dp))
            .background(brush),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = emoji,
            fontSize = (size.value * 0.42f).sp,
            textAlign = TextAlign.Center,
        )
    }
}

fun quizListPlaceholderBrush(seed: String): Brush {
    val hue = (seed.sumOf { it.code } % 360).toFloat()
    val a = hslToColor(hue, 0.45f, 0.55f)
    val b = hslToColor((hue + 40f) % 360f, 0.5f, 0.45f)
    return Brush.linearGradient(listOf(a.copy(alpha = 0.85f), b.copy(alpha = 0.75f)))
}

private fun hslToColor(h: Float, s: Float, l: Float): Color {
    val c = (1f - kotlin.math.abs(2 * l - 1f)) * s
    val x = c * (1f - kotlin.math.abs((h / 60f) % 2f - 1f))
    val m = l - c / 2f
    val (r1, g1, b1) = when {
        h < 60f -> Triple(c, x, 0f)
        h < 120f -> Triple(x, c, 0f)
        h < 180f -> Triple(0f, c, x)
        h < 240f -> Triple(0f, x, c)
        h < 300f -> Triple(x, 0f, c)
        else -> Triple(c, 0f, x)
    }
    return Color(r1 + m, g1 + m, b1 + m)
}
