package com.offline.gamerhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.offline.gamerhub.ui.NeonBlue
import com.offline.gamerhub.ui.NeonCyan
import com.offline.gamerhub.ui.NeonPink
import com.offline.gamerhub.ui.NeonPurple
import com.offline.gamerhub.ui.OfflineGamerHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OfflineGamerHubTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    OfflineGamerHubScreen()
                }
            }
        }
    }
}

private data class ThemePalette(
    val top: Color,
    val centerA: Color,
    val centerB: Color,
    val falling: Color
)

@Composable
private fun OfflineGamerHubScreen() {
    var fallingEnabled by remember { mutableStateOf(true) }
    val palette = remember {
        ThemePalette(
            top = NeonBlue,
            centerA = NeonPurple,
            centerB = NeonPink,
            falling = NeonCyan
        )
    }
    val infiniteTransition = rememberInfiniteTransition(label = "center-transition")
    val centerBlend by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "center-blend"
    )

    val centerColor = Color(
        red = lerp(palette.centerA.red, palette.centerB.red, centerBlend),
        green = lerp(palette.centerA.green, palette.centerB.green, centerBlend),
        blue = lerp(palette.centerA.blue, palette.centerB.blue, centerBlend),
        alpha = 1f
    )

    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundGradient(
            top = palette.top,
            center = centerColor,
            bottom = palette.centerB
        )
        AnimatedVisibility(visible = fallingEnabled) {
            FallingElements(color = palette.falling, size = 6.dp)
        }
        MainMenu(
            onToggleFalling = { fallingEnabled = !fallingEnabled },
            fallingEnabled = fallingEnabled
        )
    }
}

@Composable
private fun BackgroundGradient(top: Color, center: Color, bottom: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(top, center, bottom),
                    tileMode = TileMode.Clamp
                )
            )
    )
}

@Composable
private fun FallingElements(color: Color, size: Dp) {
    val infiniteTransition = rememberInfiniteTransition(label = "falling")
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "offset"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.toPx()
        val height = size.toPx()
        val step = size.toPx() * 4
        val maxY = size.height + height
        var x = 0f
        while (x < size.width) {
            val y = (offsetY * size.height + x * 0.5f) % maxY
            drawCircle(
                color = color.copy(alpha = 0.8f),
                radius = width / 2,
                center = Offset(x + width, y)
            )
            x += step
        }
    }
}

@Composable
private fun MainMenu(onToggleFalling: () -> Unit, fallingEnabled: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Offline Gamer Hub",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Totalmente offline • Neon • Personalizável",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            MenuRow("🎮 GAMER", "📅 CALENDÁRIO")
            MenuRow("🎵 MP3 / MP4", "📝 ANOTAÇÕES")
            MenuRow("🎨 PERSONALIZAR APP", "👤 PERFIL / LOGIN")
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = onToggleFalling,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text(
                    text = if (fallingEnabled) "Desativar elementos" else "Ativar elementos",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Modo offline ativo",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun MenuRow(left: String, right: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        MenuCard(title = left, modifier = Modifier.weight(1f))
        MenuCard(title = right, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun MenuCard(title: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(90.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
                    )
                )
            )
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + (stop - start) * fraction
}

@Preview(showBackground = true)
@Composable
private fun OfflineGamerHubPreview() {
    OfflineGamerHubTheme {
        OfflineGamerHubScreen()
    }
}
