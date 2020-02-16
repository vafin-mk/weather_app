package ru.test.weatherapp.domain.cases

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Test
import ru.test.weatherapp.BaseTest
import ru.test.weatherapp.Fixture
import ru.test.weatherapp.domain.cases.impl.WeatherByAddressImpl
import ru.test.weatherapp.domain.repo.WeatherRepo

class WeatherByAddressTest: BaseTest() {

    private lateinit var repo: WeatherRepo
    private lateinit var useCase: WeatherByAddress

    private val data = Fixture.generateWeatherData()

    override fun setUp() {
        super.setUp()

        repo = mockk()
        useCase = WeatherByAddressImpl(repo)
    }

    @Test
    fun testNormalFlow() {
        every { repo.weatherByAddress(any()) } returns Observable.just(data)

        useCase.getWeather("Moscow").test().apply {
            assertValue(data)
            assertTerminated()
        }
    }

    @Test
    fun testErrorSentNext() {
        val error = RuntimeException()
        every { repo.weatherByAddress(any()) } returns Observable.error(error)

        useCase.getWeather("Moscow").test().apply {
            assertError(error)
            assertTerminated()
        }
    }
}