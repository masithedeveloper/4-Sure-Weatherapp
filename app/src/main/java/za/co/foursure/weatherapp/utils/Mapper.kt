package za.co.foursure.weatherapp.utils

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}
