package za.co.foursure.weatherapp.domain.datasource.forecast

import za.co.foursure.weatherapp.db.dao.ForecastDao
import za.co.foursure.weatherapp.db.entity.ForecastEntity
import za.co.foursure.weatherapp.domain.model.ForecastResponse
import javax.inject.Inject

class ForecastLocalDataSource @Inject constructor(private val forecastDao: ForecastDao) {

    fun getForecast() = forecastDao.getForecast()

    fun insertForecast(forecast: ForecastResponse) = forecastDao.deleteAndInsert(
        ForecastEntity(forecast)
    )
}
