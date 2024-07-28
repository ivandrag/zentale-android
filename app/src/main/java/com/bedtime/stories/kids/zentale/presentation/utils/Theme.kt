package com.bedtime.stories.kids.zentale.presentation.utils

import android.app.Activity
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.bedtime.stories.kids.zentale.R

private val fontName by lazy { GoogleFont("Montserrat") }

private val fontFamily by lazy {
    FontFamily(
        Font(
            googleFont = fontName,
            fontProvider = GoogleFont.Provider(
                providerAuthority = "com.google.android.gms.fonts",
                providerPackage = "com.google.android.gms",
                certificates = R.array.com_google_android_gms_fonts_certs
            )
        )
    )
}

val darkSlateBlue = Color(0xFF1E1E2E)
val Blue = Color(0xFF007AFF)
val Teal200 = Color(0xFF03DAC5)
val Teal700 = Color(0xFF018786)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

private val DarkColorPalette = darkColors(
    primary = Blue,
    primaryVariant = Blue,
    secondary = Teal200,
    background = Black,
)

private val LightColorPalette = lightColors(
    primary = Blue,
    primaryVariant = Blue,
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
        window.statusBarColor = (if (darkTheme) Black else Blue).toArgb()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = !darkTheme
    }

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            background = Color.Black,
            primary = Blue,
            onBackground = Color.White,
        ),
        typography = MaterialTheme.typography.copy(
            displayLarge = MaterialTheme.typography.displayLarge.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal
            ),
            displayMedium = MaterialTheme.typography.displayMedium.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal
            ),
            displaySmall = MaterialTheme.typography.displaySmall.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal
                ),
            headlineLarge = MaterialTheme.typography.headlineLarge.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal
            ),
            headlineMedium = MaterialTheme.typography.headlineMedium.copy(fontFamily = fontFamily, fontWeight = FontWeight.Normal),
            headlineSmall = MaterialTheme.typography.headlineSmall.copy(fontFamily = fontFamily, fontWeight = FontWeight.Normal),
            titleLarge = MaterialTheme.typography.titleLarge.copy(fontFamily = fontFamily, fontWeight = FontWeight.Normal),
            titleMedium = MaterialTheme.typography.titleMedium.copy(fontFamily = fontFamily, fontWeight = FontWeight.Medium),
            titleSmall = MaterialTheme.typography.titleSmall.copy(fontFamily = fontFamily, fontWeight = FontWeight.Medium),
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily, fontWeight = FontWeight.Normal),
            bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontFamily = fontFamily, fontWeight = FontWeight.Normal),
            bodySmall = MaterialTheme.typography.bodySmall.copy(fontFamily = fontFamily, fontWeight = FontWeight.Normal),
            labelLarge = MaterialTheme.typography.labelLarge.copy(fontFamily = fontFamily, fontWeight = FontWeight.Medium),
            labelMedium = MaterialTheme.typography.labelMedium.copy(fontFamily = fontFamily, fontWeight = FontWeight.Medium),
            labelSmall = MaterialTheme.typography.labelSmall.copy(fontFamily = fontFamily, fontWeight = FontWeight.Medium),
        ),
        shapes = MaterialTheme.shapes,
        content = content
    )
}

