package ru.test.weatherapp.presentation.presenter

import android.content.Context
import io.mockk.every
import io.mockk.mockkClass
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.test.weatherapp.BaseTest
import ru.test.weatherapp.Fixture
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.infra.Mapper
import ru.test.weatherapp.presentation.mapper.WeatherModelMapper
import ru.test.weatherapp.presentation.model.WeatherModel

class WeatherModelMapperTest: BaseTest() {

    private lateinit var context: Context
    private lateinit var mapper: Mapper<WeatherData, WeatherModel>

    private val unknownData = Fixture.generateWeatherDataWithUnknownValues()
    private val unknownModel = Fixture.generateWeatherModelWithUnknownValues()
    private val data = Fixture.generateWeatherData()
    private val model = Fixture.generateWeatherModel()

    override fun setUp() {
        super.setUp()

        context = mockkClass(Context::class)

        every { context.getString(any()) } returns "Unknown"

        mapper = WeatherModelMapper(context)
    }

    @Test
    fun testMappingUnknownValues() {
        assertTrue(
            "Expected ${unknownModel}, but was ${mapper.map(unknownData)}",
            unknownModel == mapper.map(unknownData)
        )
    }

    @Test
    fun testMappingKnownValues() {
        assertTrue(
            "Expected $model, but was ${mapper.map(data)}",
            model == mapper.map(data)
        )
    }

    @Test
    fun testMappingDirections() {
        fun assertDirection(value: Int, mapValue: String) {
            val expectedModel = model.copy(wind = "0.0 meter/sec; $mapValue")
            val newData = data.copy(windDirection = value)
            assertTrue(
                "Expected $expectedModel, but was ${mapper.map(newData)}",
                expectedModel == mapper.map(newData)
            )
        }
        assertDirection(22, "NNE")
        assertDirection(44, "NE")
        assertDirection(66, "ENE")
        assertDirection(88, "E")
        assertDirection(111, "ESE")
        assertDirection(133, "SE")
        assertDirection(155, "SSE")
        assertDirection(177, "S")
        assertDirection(199, "SSW")
        assertDirection(222, "SW")
        assertDirection(244, "WSW")
        assertDirection(266, "W")
        assertDirection(288, "WNW")
        assertDirection(311, "NW")
        assertDirection(333, "NNW")
    }
}