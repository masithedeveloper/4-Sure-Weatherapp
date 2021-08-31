package za.co.foursure.weatherapp.ui.dashboard

import za.co.foursure.weatherapp.core.BaseViewState
import za.co.foursure.weatherapp.db.entity.CurrentWeatherEntity
import za.co.foursure.weatherapp.utils.domain.Status

class CurrentWeatherViewState(
    val status: Status,
    val error: String? = null,
    val data: CurrentWeatherEntity? = null
) : BaseViewState(status, error) {
    fun getForecast() = data
}
