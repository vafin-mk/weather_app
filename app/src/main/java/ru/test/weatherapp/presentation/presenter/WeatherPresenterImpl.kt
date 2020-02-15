package ru.test.weatherapp.presentation.presenter

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.test.weatherapp.domain.cases.WeatherByAddress
import ru.test.weatherapp.domain.cases.WeatherByPosition
import ru.test.weatherapp.domain.entity.MapPosition
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.infra.Mapper
import ru.test.weatherapp.presentation.model.WeatherModel
import ru.test.weatherapp.presentation.view.WeatherView
import javax.inject.Inject

class WeatherPresenterImpl @Inject constructor(
    private val weatherByAddress: WeatherByAddress,
    private val weatherByPosition: WeatherByPosition,
    private val weatherMapper: Mapper<WeatherData, WeatherModel>
): WeatherPresenter {

    private val subscriptions = CompositeDisposable()

    override fun bindView(view: WeatherView) {
        view.inputObservable()
            .filter { it.isNotBlank() }
            .subscribe { input -> weatherRequest(input, view) }
            .autoDispose()
    }

    override fun unbindView() {
        subscriptions.clear()
    }

    private fun weatherRequest(input: String, view: WeatherView) {
        val mapPosition = MapPosition.fromString(input)
        val request = if (mapPosition == null) {
            weatherByAddress.getWeather(input)
        } else {
            weatherByPosition.getWeather(mapPosition)
        }
        request.map { weatherMapper.map(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ weather ->
                view.updateWeatherInformation(weather)
            }, { error ->
                Log.e("WeatherApp", "Failed to fetch weather information", error)
            })
            .autoDispose()
    }

    private fun Disposable.autoDispose() = subscriptions.add(this)
}