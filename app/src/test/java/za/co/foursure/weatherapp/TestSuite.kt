package za.co.foursure.weatherapp

import android.os.Build
import za.co.foursure.weatherapp.dao.CitiesForSearchDaoTest
import za.co.foursure.weatherapp.dao.CurrentWeatherDaoTest
import za.co.foursure.weatherapp.dao.ForecastDaoTest
import za.co.foursure.weatherapp.repo.CurrentWeatherRepositoryTest
import za.co.foursure.weatherapp.repo.ForecastRepositoryTest
import za.co.foursure.weatherapp.viewModel.DashboardViewModelTest
import za.co.foursure.weatherapp.viewModel.WeatherDetailViewModelTest
import za.co.foursure.weatherapp.viewState.CurrentWeatherViewStateTest
import za.co.foursure.weatherapp.viewState.ForecastViewStateTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.R])
@RunWith(Suite::class)
@Suite.SuiteClasses(
    CitiesForSearchDaoTest::class,
    CurrentWeatherDaoTest::class,
    CurrentWeatherViewStateTest::class,
    DashboardViewModelTest::class,
    ForecastDaoTest::class,
    ForecastViewStateTest::class,
    WeatherDetailViewModelTest::class,
    ForecastRepositoryTest::class,
    CurrentWeatherRepositoryTest::class
)
class TestSuite
