package ru.test.weatherapp.presentation.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import ru.test.weatherapp.BaseTest
import ru.test.weatherapp.Fixture
import ru.test.weatherapp.domain.cases.WeatherByAddress
import ru.test.weatherapp.domain.cases.WeatherByPosition
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.infra.Mapper
import ru.test.weatherapp.presentation.model.WeatherModel
import ru.test.weatherapp.presentation.view.WeatherView

class WeatherPresenterTest: BaseTest() {

    private lateinit var weatherByAddress: WeatherByAddress
    private lateinit var weatherByPosition: WeatherByPosition
    private lateinit var weatherMapper: Mapper<WeatherData, WeatherModel>
    private lateinit var view: WeatherView
    private lateinit var presenter: WeatherPresenter

    private val inputSubject = PublishSubject.create<String>()
    private val model = Fixture.generateWeatherModel()
    private val data = Fixture.generateWeatherData()

    override fun setUp() {
        super.setUp()

        weatherByAddress = mockk()
        weatherByPosition = mockk()
        weatherMapper = mockk()
        view = mockk(relaxUnitFun = true)

        every { view.inputObservable() } returns inputSubject.hide()
        every { weatherMapper.map(any()) } returns model
        every { weatherByAddress.getWeather(any()) } returns Observable.just(data)
        every { weatherByPosition.getWeather(any()) } returns Observable.just(data)

        presenter = WeatherPresenterImpl(weatherByAddress, weatherByPosition, weatherMapper)
        presenter.bindView(view)
    }

    override fun shutdown() {
        super.shutdown()

        presenter.unbindView()
    }

    @Test
    fun testEmptyInputDoNothing() {
        inputSubject.onNext("")

        verify(exactly = 0) { weatherMapper.map(any()) }
        verify(exactly = 0) { weatherByAddress.getWeather(any()) }
        verify(exactly = 0) { weatherByPosition.getWeather(any()) }
    }

    @Test
    fun testMapPositionInput() {
        inputSubject.onNext("55.38,37.15")

        verify(exactly = 1) { weatherMapper.map(any()) }
        verify(exactly = 0) { weatherByAddress.getWeather(any()) }
        verify(exactly = 1) { weatherByPosition.getWeather(any()) }
    }

    @Test
    fun testPlainTextAddressInput() {
        inputSubject.onNext("Moscow")

        verify(exactly = 1) { weatherMapper.map(any()) }
        verify(exactly = 1) { weatherByAddress.getWeather(any()) }
        verify(exactly = 0) { weatherByPosition.getWeather(any()) }
    }
}