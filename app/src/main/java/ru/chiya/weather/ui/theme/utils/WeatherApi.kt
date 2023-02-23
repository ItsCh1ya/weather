package ru.chiya.weather.ui.theme.utils

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import org.json.JSONObject
import ru.chiya.weather.ui.theme.utils.weatherApiData.CurrentWeather
import ru.chiya.weather.ui.theme.utils.weatherApiData.WeatherApiData
import java.net.URL


fun GetWeather(locationDetails: LocationDetails): WeatherApiData {
    val policy = ThreadPolicy.Builder().permitAll().build() // TODO: rework without this, may cause freezes
    StrictMode.setThreadPolicy(policy)
    val API_URL =
        "https://api.open-meteo.com/v1/forecast?latitude=${locationDetails.latitude}&longitude=${locationDetails.longitude}&current_weather=true"
    val apiResponse = URL(API_URL).readText()
    return ParseJson(apiResponse)
}

fun ParseJson(apiResponse: String): WeatherApiData {
    val parsedJsonRoot = JSONObject(apiResponse)
    val parsedJsonCurrentWeather = parsedJsonRoot.getJSONObject("current_weather")
    val currentWeather = CurrentWeather(
        temperature = parsedJsonCurrentWeather.getDouble("temperature"),
        windspeed = parsedJsonCurrentWeather.getDouble("windspeed"),
        winddirection = parsedJsonCurrentWeather.getDouble("winddirection"),
        weathercode = parsedJsonCurrentWeather.getInt("weathercode"),
        time = parsedJsonCurrentWeather.getString("time")
    )
    val weatherApiData = WeatherApiData(
        current_weather = currentWeather,
        elevation = parsedJsonRoot.getDouble("elevation")
    )
    return weatherApiData
}