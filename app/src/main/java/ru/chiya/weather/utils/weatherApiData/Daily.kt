package ru.chiya.weather.utils.weatherApiData

data class Daily(
    val temperature_2m_max: List<Double>,
    val time: List<String>,
    val weathercode: List<Int>
)