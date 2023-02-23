package ru.chiya.weather.ui.theme.utils.weatherApiData

data class Hourly(
    val rain: List<Double>,
    val snowfall: List<Double>,
    val temperature_2m: List<Double>,
    val time: List<Int>,
    val weathercode: List<Int>
)