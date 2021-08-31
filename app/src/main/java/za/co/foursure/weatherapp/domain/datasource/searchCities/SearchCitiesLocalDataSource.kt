package za.co.foursure.weatherapp.domain.datasource.searchCities

import androidx.lifecycle.LiveData
import za.co.foursure.weatherapp.db.dao.CitiesForSearchDao
import za.co.foursure.weatherapp.db.entity.CitiesForSearchEntity
import za.co.foursure.weatherapp.domain.model.SearchResponse
import javax.inject.Inject

class SearchCitiesLocalDataSource @Inject constructor(
    private val citiesForSearchDao: CitiesForSearchDao
) {

    fun getCityByName(cityName: String?): LiveData<List<CitiesForSearchEntity>> = citiesForSearchDao.getCityByName(
        cityName
    )

    fun insertCities(response: SearchResponse) {
        response.hits
            ?.map { CitiesForSearchEntity(it) }
            ?.let { citiesForSearchDao.insertCities(it) }
    }
}
