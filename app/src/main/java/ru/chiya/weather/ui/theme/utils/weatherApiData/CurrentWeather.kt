package ru.chiya.weather.ui.theme.utils.weatherApiData

data class CurrentWeather(
    val temperature: Double,
    val time: String,
    val weathercode: Int,
    val winddirection: Double,
    val windspeed: Double
)