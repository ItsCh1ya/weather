package ru.chiya.weather.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val NightGradient = Brush.verticalGradient(
    colors = listOf(
        Color(14, 14, 65, 255),
        Color(45, 38, 88, 255),
    )
)
val MorningGradient = Brush.verticalGradient(
    colors = listOf(
        Color(230, 219, 116),
        Color(244, 164, 96),
        Color(205, 133, 63),

    )
)
val DayGradient = Brush.verticalGradient(
    colors = listOf(
        Color(135, 206, 235),
        Color(0, 191, 255),
        Color(0, 250, 154)
    )
)
val EveningGradient = Brush.verticalGradient(
    colors = listOf(
        Color(255, 160, 122),
        Color(224, 233, 122, 255),
        Color(210, 255, 192, 255),
    )
)