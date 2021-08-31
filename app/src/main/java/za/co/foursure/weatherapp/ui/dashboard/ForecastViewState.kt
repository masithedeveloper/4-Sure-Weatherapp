package za.co.foursure.weatherapp.ui.dashboard

import za.co.foursure.weatherapp.core.BaseViewState
import za.co.foursure.weatherapp.db.entity.ForecastEntity
import za.co.foursure.weatherapp.utils.domain.Status

class ForecastViewState(
    val status: Status,
    val error: String? = null,
    val data: ForecastEntity? = null
) : BaseViewState(status, error) {
    fun getForecast() = data
}
