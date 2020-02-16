package ru.test.weatherapp.data.repo

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Test
import ru.test.weatherapp.BaseTest
import ru.test.weatherapp.Fixture
import ru.test.weatherapp.data.database.api.Dao
import ru.test.weatherapp.data.database.dto.WeatherDbEntity
import ru.test.weatherapp.data.network.api.NetworkApi
import ru.test.weatherapp.data.network.dto.WeatherDto
import ru.test.weatherapp.domain.entity.MapPosition
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.domain.repo.WeatherRepo
import ru.test.weatherapp.infra.Mapper

class WeatherRepositoryTest: BaseTest() {

    private lateinit var networkApi: NetworkApi
    private lateinit var dao: Dao
    private lateinit var weatherApiMapper: Mapper<WeatherDto, WeatherData>
    private lateinit var weatherFromDbMapper: Mapper<WeatherDbEntity, WeatherData>
    private lateinit var weatherToDbMapper: Mapper<WeatherData, WeatherDbEntity>

    private lateinit var repo: WeatherRepo

    private val data = Fixture.generateWeatherData()
    private val dbData = Fixture.generateDbEntity()

    override fun setUp() {
        super.setUp()

        networkApi = mockk()
        dao = mockk(relaxUnitFun = true)
        weatherApiMapper = mockk()
        weatherFromDbMapper = mockk()
        weatherToDbMapper = mockk()

        every { weatherApiMapper.map(any()) } returns data
        every { weatherFromDbMapper.map(any()) } returns data
        every { weatherToDbMapper.map(any()) } returns dbData
        every { dao.addWeatherData(any()) } returns Completable.complete()

        repo = WeatherRepository(networkApi, dao, weatherApiMapper, weatherFromDbMapper, weatherToDbMapper)
    }

    @Test
    fun testEmptyCache() {
        every { dao.weatherByPosition(any()) } returns Maybe.empty()
        every { networkApi.weatherByPosition(any()) } returns Single.just(WeatherDto(null, null, null, null))

        repo.weatherByPosition(MapPosition(0.0, 0.0)).test().apply {
            assertValue(data)
        }
        verify(exactly = 1) { dao.addWeatherData(any()) }
    }

    @Test
    fun testNonEmptyCache() {
        every { dao.weatherByAddress(any()) } returns Maybe.just(dbData)
        every { networkApi.weatherByAddress(any()) } returns Single.just(WeatherDto(null, null, null, null))

        repo.weatherByAddress("Moscow").test().apply {
            assertValues(data, data)
        }
        verify(exactly = 1) { dao.addWeatherData(any()) }
    }

}