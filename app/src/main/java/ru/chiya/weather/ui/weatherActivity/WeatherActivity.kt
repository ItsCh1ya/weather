package ru.chiya.weather.ui.weatherActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.chiya.weather.ui.theme.*
import ru.chiya.weather.ui.theme.main.DailyWeatherList
import ru.chiya.weather.ui.theme.main.Temperature
import ru.chiya.weather.ui.theme.main.WeatherImageLarge
import ru.chiya.weather.ui.theme.main.WeatherShortDesc
import ru.chiya.weather.utils.GetWeather
import ru.chiya.weather.utils.LocationDetails
import ru.chiya.weather.utils.weatherApiData.WMOCodes
import ru.chiya.weather.utils.weatherApiData.WeatherApiData
import java.util.*

class WeatherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weather = mutableStateOf<WeatherApiData?>(null)

        lifecycleScope.launch(Dispatchers.IO) {
            val bundle = intent.extras!!
            val locationLatitude = bundle.getDouble("latitude")
            val locationLongitude = bundle.getDouble("longitude")
            val locationDetails = LocationDetails(
                locationLatitude, locationLongitude
            )
            weather.value = GetWeather(locationDetails)
        }
        setContent {
            WeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (weather.value != null) {
                        WeatherScreen(weather = weather.value!!)
                    } else {
                        Text(text = "Loading weather, please wait...")
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherScreen(weather: WeatherApiData) {
    val currentTime = Date()
    val calendar = Calendar.getInstance()
    calendar.time = currentTime
    val hour = calendar.get(Calendar.HOUR_OF_DAY)

    val isDayTime = hour in 6..17
    val gradient = when (hour) {
        in 6..11 -> MorningGradient
        in 12..17 -> DayGradient
        in 18..20 -> EveningGradient
        else -> NightGradient
    }

    Column(
        modifier = Modifier.background(brush = gradient),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val wmoCodes = WMOCodes(LocalContext.current)
        val wmoCodeDesc = wmoCodes.weatherCodesMap[weather.current_weather.weathercode]
        Column(modifier = Modifier.padding(top = 30.dp)) {
            Temperature(temperature = weather.current_weather.temperature.toString())
            WeatherImageLarge(wmoCodes, weather, isDayTime)
            WeatherShortDesc(description = wmoCodeDesc!!)
        }
        Column(modifier = Modifier.padding(bottom = 30.dp)) {
            DailyWeatherList(wmoCodes, weather, isDayTime)
        }
    }
}