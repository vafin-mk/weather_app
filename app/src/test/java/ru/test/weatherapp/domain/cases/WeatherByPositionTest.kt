package ru.test.weatherapp.domain.cases

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Test
import ru.test.weatherapp.BaseTest
import ru.test.weatherapp.Fixture
import ru.test.weatherapp.domain.cases.impl.WeatherByPositionImpl
import ru.test.weatherapp.domain.entity.MapPosition
import ru.test.weatherapp.domain.repo.WeatherRepo

class WeatherByPositionTest: BaseTest() {

    private lateinit var repo: WeatherRepo
    private lateinit var useCase: WeatherByPosition

    private val data = Fixture.generateWeatherData()

    override fun setUp() {
        super.setUp()

        repo = mockk()
        useCase = WeatherByPositionImpl(repo)
    }

    @Test
    fun testNormalFlow() {
        every { repo.weatherByPosition(any()) } returns Observable.just(data)

        useCase.getWeather(MapPosition(0.0, 0.0)).test().apply {
            assertValue(data)
            assertTerminated()
        }
    }

    @Test
    fun testErrorSentNext() {
        val error = RuntimeException()
        every { repo.weatherByPosition(any()) } returns Observable.error(error)

        useCase.getWeather(MapPosition(0.0, 0.0)).test().apply {
            assertError(error)
            assertTerminated()
        }
    }
}