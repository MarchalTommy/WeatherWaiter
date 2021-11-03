package com.aki.weatherwaiter_exomind.data

import com.aki.weatherwaiter_exomind.BuildConfig
import com.aki.weatherwaiter_exomind.model.GetWeatherByCityName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("weather?units=metric&")
    suspend fun getWeatherByCityName(
        @Query("q") cityName: String,
        @Query("appid") api: String = BuildConfig.WEATHER_API
    ): Response<GetWeatherByCityName>
}