package za.co.foursure.weatherapp.di

import za.co.foursure.weatherapp.domain.usecase.CurrentWeatherUseCase
import za.co.foursure.weatherapp.domain.usecase.ForecastUseCase
import za.co.foursure.weatherapp.repo.CurrentWeatherRepository
import za.co.foursure.weatherapp.repo.ForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherUseCase(currentWeatherRepository: CurrentWeatherRepository) =
        CurrentWeatherUseCase(currentWeatherRepository)

    @Provides
    @Singleton
    fun provideForecastUseCase(forecastRepository: ForecastRepository) =
        ForecastUseCase(forecastRepository)
}