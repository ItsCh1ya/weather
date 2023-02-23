package ru.chiya.weather.ui.theme.utils

import java.net.HttpURLConnection
import java.net.URL

class WeatherApi(locationDetails: LocationDetails) {
    init {

    }
    fun getWeather(){
        val url = URL("http://www.google.com/")

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"  // optional default is GET

        }
    }
}