package ru.test.weatherapp.presentation.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_weather.view.*
import ru.test.weatherapp.presentation.model.WeatherModel
import ru.test.weatherapp.presentation.view.WeatherView

class WeatherView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttrs: Int = 0
): ConstraintLayout(context, attributeSet, defStyleAttrs), WeatherView {

    private val inputSubject = PublishSubject.create<String>()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        searchButton.setOnClickListener {
            inputSubject.onNext(searchInput.text.toString())
        }
    }

    override fun inputObservable(): Observable<String> = inputSubject.hide()

    override fun updateWeatherInformation(model: WeatherModel) {
        updateDate.text = model.updateDate
        temperature.text = model.temperature
        feelsLikeValue.text = model.feelsLikeTemperature
        pressureValue.text = model.pressure
        humidityValue.text = model.humidity
        windValue.text = model.wind
    }
}