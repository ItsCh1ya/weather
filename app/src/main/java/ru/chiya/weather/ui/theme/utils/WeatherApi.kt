package ru.chiya.weather.ui.theme.utils

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import org.json.JSONObject
import ru.chiya.weather.ui.theme.utils.weatherApiData.CurrentWeather
import ru.chiya.weather.ui.theme.utils.weatherApiData.Daily
import ru.chiya.weather.ui.theme.utils.weatherApiData.WeatherApiData
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


fun GetWeather(locationDetails: LocationDetails): WeatherApiData {
    val policy =
        ThreadPolicy.Builder().permitAll().build() // TODO: rework without this, may cause freezes
    StrictMode.setThreadPolicy(policy)

    val API_URL = "https://api.open-meteo.com/v1/forecast?" +
            "latitude=${locationDetails.latitude}&" +
            "longitude=${locationDetails.longitude}&" +
            "daily=weathercode,apparent_temperature_max&" +
            "current_weather=true&" +
            "timeformat=unixtime&" +
            "timezone=Europe%2FMoscow"
    
    val apiResponse = URL(API_URL).readText()
    return ParseJson(apiResponse)
}

fun ParseJson(apiResponse: String): WeatherApiData {
    val parsedJsonRoot = JSONObject(apiResponse)
    val parsedJsonCurrentWeather = parsedJsonRoot.getJSONObject("current_weather")
    val parsedJsonDaily = parsedJsonRoot.getJSONObject("daily")
    val parsedJsonDailyTemp = parsedJsonDaily.getJSONArray("apparent_temperature_max")
    val parsedJsonDailyDays = parsedJsonDaily.getJSONArray("time")
    val parsedJsonDailyWeatherCodes = parsedJsonDaily.getJSONArray("weathercode")

    val currentWeather = CurrentWeather(
        temperature = parsedJsonCurrentWeather.getDouble("temperature"),
        windspeed = parsedJsonCurrentWeather.getDouble("windspeed"),
        winddirection = parsedJsonCurrentWeather.getDouble("winddirection"),
        weathercode = parsedJsonCurrentWeather.getInt("weathercode"),
        time = parsedJsonCurrentWeather.getString("time")
    )
    val daily = Daily(
        temperature_2m_max = List(parsedJsonDailyTemp.length()) { index ->
            parsedJsonDailyTemp.getDouble(index)
        },
        time = List(parsedJsonDailyDays.length()) { index ->
            getAbbreviatedDayOfWeek(parsedJsonDailyDays.getLong(index))
        },
        weathercode = List(parsedJsonDailyWeatherCodes.length()) { index ->
            parsedJsonDailyWeatherCodes.getInt(index)
        },
    )
    return WeatherApiData(
        current_weather = currentWeather,
        daily = daily,
        elevation = parsedJsonRoot.getDouble("elevation")
    )
}

fun getAbbreviatedDayOfWeek(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("EEE", Locale.getDefault())
    val date = Date(timestamp * 1000)
    return dateFormat.format(date)
}