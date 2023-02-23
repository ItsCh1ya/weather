package ru.chiya.weather.ui.theme.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Temperature(temperature: String){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "${temperature}°", style = MaterialTheme.typography.h1)
    }
}

@Composable
fun WeatherShortDesc(description: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = description, style = MaterialTheme.typography.subtitle1)
    }
}