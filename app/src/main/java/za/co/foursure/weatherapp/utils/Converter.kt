package za.co.foursure.weatherapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import za.co.foursure.weatherapp.R
import java.util.Locale

class Converter(
    val context: Context,
    private val sharedPreferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context),
    locale: Locale = Locale.getDefault(),
    private val zoneId: ZoneId = ZoneId.systemDefault()
) {

    private val dateTimeFormat = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
            .withLocale(locale)
            .withZone(zoneId)

    fun temperature(value: Float): String =
            sharedPreferences.getString("Temperature", "celsius").let { units ->
                when (units) {
                    "celsius" -> kelvinToCelsius(value) to R.string.temperature_celsius
                    else -> kelvinToFahrenheit(value) to R.string.temperature_fahrenheit
                }
            }.let { (newValue, template) ->
                context.getString(template, newValue)
            }

    private fun kelvinToCelsius(value: Float): Float = value - 273.15f
    private fun kelvinToFahrenheit(value: Float): Float = (9f / 5f * (value - 273.15f)) + 32
}
