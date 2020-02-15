package ru.test.weatherapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.test.weatherapp.R
import ru.test.weatherapp.infra.di.Injector
import ru.test.weatherapp.presentation.presenter.WeatherPresenter
import ru.test.weatherapp.presentation.view.WeatherView
import javax.inject.Inject

class WeatherFragment: Fragment() {

    @Inject
    lateinit var presenter: WeatherPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onStart() {
        super.onStart()

        presenter.bindView(view as WeatherView)
    }

    override fun onStop() {
        super.onStop()

        presenter.unbindView()
    }
}