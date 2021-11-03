package com.aki.weatherwaiter_exomind.data

import com.aki.weatherwaiter_exomind.model.GetWeatherByCityName
import retrofit2.Response
import java.lang.Exception

class ApiClient(private val service: Service) {

    suspend fun getWeatherByCityName(cityName: String): SimpleResponse<GetWeatherByCityName> {
        return safeApiCall { service.getWeatherByCityName(cityName) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}
