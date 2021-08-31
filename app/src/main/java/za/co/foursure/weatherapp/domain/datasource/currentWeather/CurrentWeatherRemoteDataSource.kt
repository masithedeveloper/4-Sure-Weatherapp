package za.co.foursure.weatherapp.domain.datasource.currentWeather

import za.co.foursure.weatherapp.domain.WeatherAppAPI
import za.co.foursure.weatherapp.domain.model.CurrentWeatherResponse
import io.reactivex.Single
import javax.inject.Inject

class CurrentWeatherRemoteDataSource @Inject constructor(private val api: WeatherAppAPI) {

    fun getCurrentWeatherByGeoCords(lat: Double, lon: Double, units: String): Single<CurrentWeatherResponse> = api.getCurrentByGeoCords(
        lat,
        lon,
        units
    )
}
