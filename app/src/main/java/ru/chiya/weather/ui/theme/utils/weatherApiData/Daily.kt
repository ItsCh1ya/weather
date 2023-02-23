package ru.chiya.weather.ui.theme.utils.weatherApiData

data class Daily(
    val sunrise: List<Int>,
    val sunset: List<Int>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val time: List<Int>,
    val weathercode: List<Int>,
    val windspeed_10m_max: List<Double>
)