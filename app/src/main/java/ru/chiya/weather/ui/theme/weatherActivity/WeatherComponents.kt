package ru.chiya.weather.ui.theme.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.chiya.weather.ui.theme.utils.weatherApiData.WMOCodes
import ru.chiya.weather.ui.theme.utils.weatherApiData.WeatherApiData

@Composable
fun Temperature(temperature: String) {
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

@Composable
fun WeatherImageLarge(
    wmoCodes: WMOCodes,
    weather: WeatherApiData,
    isDayTime: Boolean
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Image(
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
                .padding(top = 10.dp),
            painter = painterResource(
                id = wmoCodes.getWeatherImageResource(
                    weather.current_weather.weathercode,
                    isDayTime
                )
            ),
            contentDescription = "ImageDesc",
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun DailyWeatherList(
    wmoCodes: WMOCodes,
    weather: WeatherApiData,
    isDayTime: Boolean
) {
    LazyRow() {
        items(6) { i ->
            Box(modifier = Modifier.padding(horizontal = 4.dp)) {
                Image(
                    painter = painterResource(
                        id = wmoCodes.getWeatherImageResource(
                            weather.daily.weathercode[i],
                            isDayTime
                        )
                    ), contentDescription = "", modifier = Modifier
                        .width(100.dp)
                        .align(Alignment.Center)
                )
                Box(
                    Modifier
                        .clip(CircleShape)
                        .background(Color(0x7800FFAE))
                        .align(Alignment.TopStart)
                ) {
                    Text(
                        text = weather.daily.time[i],
                        modifier = Modifier.padding(2.dp)
                    )
                }
                Box(
                    Modifier
                        .clip(CircleShape)
                        .background(Color(0x7FA600FF))
                        .align(Alignment.BottomEnd)
                ) {
                    Text(
                        text = weather.daily.temperature_2m_max[i].toString(),
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
        }
    }
}