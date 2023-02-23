package ru.chiya.weather.ui.theme.weatherActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.chiya.weather.ui.theme.WeatherTheme
import ru.chiya.weather.ui.theme.utils.LocationDetails

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
                    val bundle = intent.extras
                    val locationLatitude = bundle!!.getDouble("latitude")
                    val locationLongitude = bundle.getDouble("longitude")
                    val locationDetails = LocationDetails(
                        locationLatitude, locationLongitude
                    )

                }
            }
        }
    }
}