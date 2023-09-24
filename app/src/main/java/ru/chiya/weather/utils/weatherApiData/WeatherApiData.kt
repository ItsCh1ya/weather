package ru.chiya.weather.utils.weatherApiData

data class WeatherApiData(
    val current_weather: CurrentWeather,
    val daily: Daily,
    val elevation: Double
)