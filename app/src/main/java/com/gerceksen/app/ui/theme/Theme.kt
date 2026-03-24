package com.gerceksen.app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = CoralPrimary,
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFDAD4),
    onPrimaryContainer = OnWarmDark,
    secondary = AccentAmber,
    onSecondary = OnWarmDark,
    tertiary = AccentViolet,
    onTertiary = Color(0xFFFFFFFF),
    background = WarmSurface,
    onBackground = OnWarmDark,
    surface = WarmSurface,
    onSurface = OnWarmDark,
    surfaceVariant = WarmSurfaceVariant,
    onSurfaceVariant = Color(0xFF5C4F4A),
    outline = Color(0xFF85736C),
    surfaceContainerLowest = Color(0xFFFFFBF8),
    surfaceContainerLow = Color(0xFFFFF3ED),
    surfaceContainer = WarmSurfaceVariant,
    surfaceContainerHigh = Color(0xFFE5D9D3),
    surfaceContainerHighest = Color(0xFFD8CCC5),
)

private val DarkColors = darkColorScheme(
    primary = CoralPrimaryDark,
    onPrimary = OnWarmDark,
    primaryContainer = Color(0xFF8B3D2E),
    onPrimaryContainer = OnWarmLight,
    secondary = AccentAmber,
    onSecondary = OnWarmDark,
    tertiary = AccentViolet,
    onTertiary = OnWarmLight,
    background = WarmSurfaceDark,
    onBackground = OnWarmLight,
    surface = WarmSurfaceDark,
    onSurface = OnWarmLight,
    surfaceVariant = WarmSurfaceVariantDark,
    onSurfaceVariant = Color(0xFFD7C2B8),
    outline = Color(0xFF9A8A82),
    surfaceContainerLowest = Color(0xFF121110),
    surfaceContainerLow = Color(0xFF181615),
    surfaceContainer = WarmSurfaceVariantDark,
    surfaceContainerHigh = Color(0xFF383330),
    surfaceContainerHighest = Color(0xFF43403C),
)

@Composable
fun GercekSenTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = GercekSenTypography,
        shapes = GercekSenShapes,
        content = content,
    )
}
