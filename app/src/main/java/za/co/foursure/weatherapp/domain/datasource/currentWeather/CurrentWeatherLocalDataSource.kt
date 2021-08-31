package za.co.foursure.weatherapp.domain.datasource.currentWeather

import za.co.foursure.weatherapp.db.dao.CurrentWeatherDao
import za.co.foursure.weatherapp.db.entity.CurrentWeatherEntity
import za.co.foursure.weatherapp.domain.model.CurrentWeatherResponse
import javax.inject.Inject

class CurrentWeatherLocalDataSource @Inject constructor(
    private val currentWeatherDao: CurrentWeatherDao
) {

    fun getCurrentWeather() = currentWeatherDao.getCurrentWeather()

    fun insertCurrentWeather(currentWeather: CurrentWeatherResponse) = currentWeatherDao.deleteAndInsert(
        CurrentWeatherEntity(currentWeather)
    )
}
