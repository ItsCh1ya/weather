package ru.chiya.weather.ui.theme.utils.weatherApiData

import android.content.Context
import android.provider.Settings.Global.getString
import ru.chiya.weather.R

class WMOCodes(context: Context) {
    var weatherCodesMap = mapOf(
        0 to context.getString(R.string.weather_desc_0),
        1 to context.getString(R.string.weather_desc_1),
        2 to context.getString(R.string.weather_desc_2),
        3 to context.getString(R.string.weather_desc_3),
        45 to context.getString(R.string.weather_desc_45),
        48 to context.getString(R.string.weather_desc_48),
        51 to context.getString(R.string.weather_desc_51),
        53 to context.getString(R.string.weather_desc_53),
        55 to context.getString(R.string.weather_desc_55),
        56 to context.getString(R.string.weather_desc_56),
        57 to context.getString(R.string.weather_desc_57),
        61 to context.getString(R.string.weather_desc_61),
        63 to context.getString(R.string.weather_desc_63),
        65 to context.getString(R.string.weather_desc_65),
        66 to context.getString(R.string.weather_desc_66),
        67 to context.getString(R.string.weather_desc_67),
        71 to context.getString(R.string.weather_desc_71),
        73 to context.getString(R.string.weather_desc_73),
        75 to context.getString(R.string.weather_desc_75),
        77 to context.getString(R.string.weather_desc_77),
        80 to context.getString(R.string.weather_desc_80),
        81 to context.getString(R.string.weather_desc_81),
        82 to context.getString(R.string.weather_desc_82),
        85 to context.getString(R.string.weather_desc_85),
        86 to context.getString(R.string.weather_desc_86),
        95 to context.getString(R.string.weather_desc_95),
        96 to context.getString(R.string.weather_desc_96),
        99 to context.getString(R.string.weather_desc_99)
    )

    fun getWeatherImageResource(weatherCode: Int, isDayTime: Boolean): Int {
        return when (weatherCode) {
            0 -> if (isDayTime) R.drawable.clear_day else R.drawable.clear_night
            1 -> R.drawable.clear_night
            2 -> if (isDayTime) R.drawable.cloudy_day else R.drawable.clear_night
            3 -> R.drawable.cloudy_night
            45, 48 -> R.drawable.foggy
            51, 53, 55 -> R.drawable.rain
            56, 57, 66, 67, 77 -> R.drawable.sleet
            61, 63, 65, 71, 73, 75, 85, 86 -> R.drawable.snow
            80, 81 -> R.drawable.rain
            82, 95, 96, 99 -> R.drawable.thunder
            else -> if (isDayTime) R.drawable.clear_day else R.drawable.clear_night // default image
        }
    }
}