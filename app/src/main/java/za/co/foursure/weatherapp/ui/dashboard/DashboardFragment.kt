package za.co.foursure.weatherapp.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import za.co.foursure.weatherapp.R
import za.co.foursure.weatherapp.core.BaseFragment
import za.co.foursure.weatherapp.core.Constants
import za.co.foursure.weatherapp.databinding.FragmentDashboardBinding
import za.co.foursure.weatherapp.domain.model.ListItem
import za.co.foursure.weatherapp.domain.usecase.CurrentWeatherUseCase
import za.co.foursure.weatherapp.domain.usecase.ForecastUseCase
import za.co.foursure.weatherapp.ui.dashboard.forecast.ForecastAdapter
import za.co.foursure.weatherapp.ui.main.MainActivity
import za.co.foursure.weatherapp.utils.Converter
import za.co.foursure.weatherapp.utils.extensions.isNetworkAvailable
import za.co.foursure.weatherapp.utils.extensions.observeWith


@AndroidEntryPoint
class DashboardFragment : BaseFragment<DashboardFragmentViewModel, FragmentDashboardBinding>(
    R.layout.fragment_dashboard,
    DashboardFragmentViewModel::class.java,
), OnMapReadyCallback {
    private lateinit var converter: Converter
    private lateinit var lon: String
    private lateinit var lat: String

    override fun init() {
        super.init()
        initForecastAdapter()
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(
            android.R.transition.move
        )

        lat = binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LAT, "").orEmpty()
        lon = binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LON, "").orEmpty()

        if (lat?.isNotEmpty() && lon?.isNotEmpty()) {
            binding.viewModel?.setCurrentWeatherParams(
                CurrentWeatherUseCase.CurrentWeatherParams(
                    lat,
                    lon,
                    isNetworkAvailable(requireContext()),
                    Constants.Coords.METRIC
                )
            )
            binding.viewModel?.setForecastParams(
                ForecastUseCase.ForecastParams(
                    lat,
                    lon,
                    isNetworkAvailable(requireContext()),
                    Constants.Coords.METRIC
                )
            )
        }

        binding.viewModel?.getForecastViewState()?.observeWith(
            viewLifecycleOwner
        ) {
            with(binding) {
                viewState = it
                it.data?.list?.let { forecasts -> initForecast(forecasts) }
                (activity as MainActivity).viewModel.toolbarTitle.set(
                    it.data?.city?.getCityAndCountry()
                )
            }
        }

        binding.viewModel?.getCurrentWeatherViewState()?.observeWith(
            viewLifecycleOwner
        ) {
            with(binding) {
                containerForecast.viewState = it
            }
        }

        binding.containerForecast.mapview.getMapAsync(this)

    }
    var map: GoogleMap? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View{
        super.onCreateView(inflater,
            container,
        savedInstanceState)

        binding.containerForecast.mapview.onCreate(savedInstanceState)

        return super.requireView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        converter = Converter(context)
    }

    private fun initForecastAdapter() {
        val adapter = ForecastAdapter { item, cardView, forecastIcon, dayOfWeek, temp, tempMaxMin ->
            val action = DashboardFragmentDirections.actionDashboardFragmentToWeatherDetailFragment(
                item
            )
            findNavController()
                .navigate(
                    action,
                    FragmentNavigator.Extras.Builder()
                        .addSharedElements(
                            mapOf(
                                cardView to cardView.transitionName,
                                forecastIcon to forecastIcon.transitionName,
                                dayOfWeek to dayOfWeek.transitionName,
                                temp to temp.transitionName,
                                tempMaxMin to tempMaxMin.transitionName
                            )
                        )
                        .build()
                )
        }

        binding.recyclerForecast.adapter = adapter
        binding.recyclerForecast.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        postponeEnterTransition()
        binding.recyclerForecast.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    private fun initForecast(list: List<ListItem>) {
        (binding.recyclerForecast.adapter as ForecastAdapter).submitList(list)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val currentCity = LatLng(lat.toDouble(), lon.toDouble())
        googleMap.addMarker(
            MarkerOptions()
                .position(currentCity)
                .title("Marker in Current City")
        )
    }
}
