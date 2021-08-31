package za.co.foursure.weatherapp.di

import za.co.foursure.weatherapp.domain.datasource.currentWeather.CurrentWeatherLocalDataSource
import za.co.foursure.weatherapp.domain.datasource.currentWeather.CurrentWeatherRemoteDataSource
import za.co.foursure.weatherapp.domain.datasource.forecast.ForecastLocalDataSource
import za.co.foursure.weatherapp.domain.datasource.forecast.ForecastRemoteDataSource
import za.co.foursure.weatherapp.domain.datasource.searchCities.SearchCitiesLocalDataSource
import za.co.foursure.weatherapp.domain.datasource.searchCities.SearchCitiesRemoteDataSource
import za.co.foursure.weatherapp.repo.CurrentWeatherRepository
import za.co.foursure.weatherapp.repo.ForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherRepository(
        currentWeatherRemoteDataSource: CurrentWeatherRemoteDataSource,
        currentWeatherLocalDataSource: CurrentWeatherLocalDataSource,
    ) = CurrentWeatherRepository(currentWeatherRemoteDataSource, currentWeatherLocalDataSource)

    @Provides
    @Singleton
    fun provideForecastRepository(
        forecastRemoteDataSource: ForecastRemoteDataSource,
        forecastLocalDataSource: ForecastLocalDataSource,
    ) = ForecastRepository(forecastRemoteDataSource, forecastLocalDataSource)

}