package ru.chiya.weather.ui.theme.utils.weatherApiData

data class DailyUnits(
    val sunrise: String,
    val sunset: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val weathercode: String,
    val windspeed_10m_max: String
)