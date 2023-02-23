package ru.chiya.weather.ui.theme.weatherActivity

import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.chiya.weather.ui.theme.*
import ru.chiya.weather.ui.theme.WeatherTheme
import ru.chiya.weather.ui.theme.main.Temperature
import ru.chiya.weather.ui.theme.main.WeatherShortDesc
import ru.chiya.weather.ui.theme.utils.GetWeather
import ru.chiya.weather.ui.theme.utils.LocationDetails
import ru.chiya.weather.ui.theme.utils.weatherApiData.WMOCodes
import ru.chiya.weather.ui.theme.utils.weatherApiData.WeatherApiData
import java.util.*

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

    Column(modifier = Modifier.background(brush = gradient)) {
        val wmoCodes = WMOCodes(LocalContext.current)
        val wmoCodeDesc = wmoCodes.weatherCodesMap[weather.current_weather.weathercode]
        Spacer(modifier = Modifier.height(50.dp))
        Temperature(temperature = weather.current_weather.temperature.toString())
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Image(
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .padding(top = 10.dp),
                painter = painterResource(
                    id = wmoCodes.getWeatherImageResource(
                        weather.current_weather.weathercode,
                        isDayTime
                    )
                ),
                contentDescription = "ImageDesc",
                contentScale = ContentScale.Fit
            )
        }
        WeatherShortDesc(description = wmoCodeDesc!!)
    }
}