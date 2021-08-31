package za.co.foursure.weatherapp.ui.weather_detail.weatherHourOfDay

import androidx.databinding.ObservableField
import za.co.foursure.weatherapp.core.BaseViewModel
import za.co.foursure.weatherapp.domain.model.ListItem
import javax.inject.Inject

class WeatherHourOfDayItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<ListItem>()
}
