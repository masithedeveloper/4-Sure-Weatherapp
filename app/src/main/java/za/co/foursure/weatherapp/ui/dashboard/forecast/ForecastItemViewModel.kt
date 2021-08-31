package za.co.foursure.weatherapp.ui.dashboard.forecast

import androidx.databinding.ObservableField
import za.co.foursure.weatherapp.core.BaseViewModel
import za.co.foursure.weatherapp.domain.model.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForecastItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<ListItem>()
}
