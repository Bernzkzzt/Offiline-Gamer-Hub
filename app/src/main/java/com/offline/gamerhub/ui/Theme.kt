package com.offline.gamerhub.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val NeonBlue = Color(0xFF1B9FFF)
val NeonPurple = Color(0xFF8F5BFF)
val NeonPink = Color(0xFFFF4FD8)
val NeonCyan = Color(0xFF2CF6FF)

private val OfflineGamerColors = darkColorScheme(
    primary = NeonBlue,
    secondary = NeonPurple,
    tertiary = NeonPink,
    background = Color.Black,
    surface = Color(0xFF101018)
)

@Composable
fun OfflineGamerHubTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = OfflineGamerColors,
        content = content
    )
}
