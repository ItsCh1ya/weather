package ru.chiya.weather.ui.theme.weatherActivity

import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import ru.chiya.weather.ui.theme.*
import ru.chiya.weather.ui.theme.WeatherTheme
import ru.chiya.weather.ui.theme.main.Temperature
import ru.chiya.weather.ui.theme.main.WeatherShortDesc
import ru.chiya.weather.ui.theme.utils.GetWeather
import ru.chiya.weather.ui.theme.utils.LocationDetails
import ru.chiya.weather.ui.theme.utils.weatherApiData.WMOCodes
import ru.chiya.weather.ui.theme.utils.weatherApiData.WeatherApiData

class WeatherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val bundle = intent.extras!!
                    val locationLatitude = bundle.getDouble("latitude")
                    val locationLongitude = bundle.getDouble("longitude")
                    val locationDetails = LocationDetails(
                        locationLatitude, locationLongitude
                    )
                    val weather = GetWeather(locationDetails)
                    WeatherScreen(weather)
                }
            }
        }
    }
}

@Composable
fun WeatherScreen(weather: WeatherApiData) {
    Column(modifier = Modifier.background(brush = NightGradient)) {
        Temperature(temperature = weather.current_weather.temperature.toString())
        val wmoCodeDesc =
            WMOCodes(LocalContext.current).weatherCodesMap[weather.current_weather.weathercode]
        WeatherShortDesc(description = wmoCodeDesc!!)
    }
}