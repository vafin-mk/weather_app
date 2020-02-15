package ru.test.weatherapp.presentation.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import io.reactivex.Observable
import ru.test.weatherapp.presentation.model.WeatherModel
import ru.test.weatherapp.presentation.view.WeatherView

class WeatherView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttrs: Int = 0
): ConstraintLayout(context, attributeSet, defStyleAttrs), WeatherView {

    override fun inputObservable(): Observable<String> = Observable.never()

    override fun updateWeatherInformation(model: WeatherModel) {

    }
}