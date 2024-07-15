package com.bedtime.stories.kids.zentale.presentation.utils

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

val darkSlateBlue = Color(0xFF1E1E2E)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Teal700 = Color(0xFF018786)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

private val DarkColorPalette = darkColors(
    primary = Purple700,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Black,
)

private val LightColorPalette = lightColors(
    primary = Purple700,
    primaryVariant = Purple700,
    secondary = Teal200,
    onPrimary = White,
    onSecondary = Black
)

@Composable
fun ZentaleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val activity = LocalContext.current as? Activity
    val window = activity?.window
    window?.let {
        window.statusBarColor = (if (darkTheme) Black else Purple700).toArgb()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = !darkTheme
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

val Typography = Typography(
    defaultFontFamily = FontFamily.Default,
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
    ),
    // Define other text styles as needed
)

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(12.dp)
)