package ru.test.weatherapp.infra.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import ru.test.weatherapp.data.network.api.NetworkApi
import ru.test.weatherapp.data.network.api.NetworkApiImpl
import ru.test.weatherapp.data.network.api.RestApi
import ru.test.weatherapp.infra.di.ApiKey

@Module
class NetworkModule {

    @Provides
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    @Provides
    fun provideRestClient(httpClient: OkHttpClient): RestApi {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()

        return retrofit.create(RestApi::class.java)
    }

    @Provides
    fun provideNetworkApi(
        restClient: RestApi,
        @ApiKey apiKey: String
    ): NetworkApi {
        return NetworkApiImpl(restClient, apiKey)
    }
}