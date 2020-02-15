package ru.test.weatherapp.presentation.presenter

import android.util.Log
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.test.weatherapp.presentation.model.WeatherModel
import ru.test.weatherapp.presentation.view.WeatherView
import javax.inject.Inject

class WeatherPresenterImpl @Inject constructor(

): WeatherPresenter {

    private val subscriptions = CompositeDisposable()

    override fun bindView(view: WeatherView) {
        view.inputObservable()
            .flatMapSingle { input -> weatherRequest(input) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ weather ->
                view.updateWeatherInformation(weather)
            }, { error ->
                Log.e("WeatherApp", "Failed to fetch weather information", error)
            })
            .autoDispose()
    }

    override fun unbindView() {
        subscriptions.clear()
    }

    private fun weatherRequest(input: String): Single<WeatherModel> {
        //todo
        return Single.just(WeatherModel(100500.0))
    }

    private fun Disposable.autoDispose() = subscriptions.add(this)
}