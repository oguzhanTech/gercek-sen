package com.gerceksen.app.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.gerceksen.app.model.ResultAccent
import com.gerceksen.app.ui.theme.AccentAmber
import com.gerceksen.app.ui.theme.AccentRose
import com.gerceksen.app.ui.theme.AccentTeal
import com.gerceksen.app.ui.theme.AccentViolet
import com.gerceksen.app.ui.theme.CoralPrimary

data class AccentStyle(
    val primary: Color,
    val container: Color,
    val onContainer: Color,
)

@Composable
fun accentStyle(accent: ResultAccent): AccentStyle {
    return when (accent) {
        ResultAccent.Coral -> AccentStyle(
            primary = CoralPrimary,
            container = CoralPrimary.copy(alpha = 0.14f),
            onContainer = MaterialTheme.colorScheme.onSurface,
        )
        ResultAccent.Amber -> AccentStyle(
            primary = AccentAmber,
            container = AccentAmber.copy(alpha = 0.18f),
            onContainer = MaterialTheme.colorScheme.onSurface,
        )
        ResultAccent.Violet -> AccentStyle(
            primary = AccentViolet,
            container = AccentViolet.copy(alpha = 0.16f),
            onContainer = MaterialTheme.colorScheme.onSurface,
        )
        ResultAccent.Teal -> AccentStyle(
            primary = AccentTeal,
            container = AccentTeal.copy(alpha = 0.16f),
            onContainer = MaterialTheme.colorScheme.onSurface,
        )
        ResultAccent.Rose -> AccentStyle(
            primary = AccentRose,
            container = AccentRose.copy(alpha = 0.16f),
            onContainer = MaterialTheme.colorScheme.onSurface,
        )
    }
}
