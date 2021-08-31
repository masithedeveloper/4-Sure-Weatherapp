package za.co.foursure.weatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import za.co.foursure.weatherapp.db.dao.CitiesForSearchDao
import za.co.foursure.weatherapp.db.dao.CurrentWeatherDao
import za.co.foursure.weatherapp.db.dao.ForecastDao
import za.co.foursure.weatherapp.db.entity.CitiesForSearchEntity
import za.co.foursure.weatherapp.db.entity.CurrentWeatherEntity
import za.co.foursure.weatherapp.db.entity.ForecastEntity
import za.co.foursure.weatherapp.utils.typeconverters.DataConverter

@Database(
    entities = [
        ForecastEntity::class,
        CurrentWeatherEntity::class,
        CitiesForSearchEntity::class
    ],
    version = 2
)
@TypeConverters(DataConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao

    abstract fun currentWeatherDao(): CurrentWeatherDao

    abstract fun citiesForSearchDao(): CitiesForSearchDao
}
