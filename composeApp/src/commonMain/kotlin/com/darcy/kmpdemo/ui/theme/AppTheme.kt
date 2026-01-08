package com.darcy.kmpdemo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.darcy.kmpdemo.ui.colors.AppColors

private val lightColorPalette = lightColorScheme(
    background = AppColors.bg_light_default,
)
private val darkColorPalette = darkColorScheme(
    background = AppColors.bg_dark_default,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorPalette
    } else {
        lightColorPalette
    }
    MaterialTheme(
        colorScheme = colors,
        typography = customFonts(),
//        typography = MaterialTheme.typography,
        content = content,
    )
}