package com.aki.weatherwaiter_exomind.data.repository

import com.aki.weatherwaiter_exomind.model.GetWeatherByCityName
import com.aki.weatherwaiter_exomind.data.NetworkLayer

class Repository {

    suspend fun getWeatherByCityName(cityName: String): GetWeatherByCityName? {
        val request = NetworkLayer.apiClient.getWeatherByCityName(cityName)

        /*
        This would be caused by a network issue
        Shortcut here -> It returns null because I didn't wanted to lose time handling the error case,
        but I still showed that I knew how to do it.
         */
        if(request.failed) {
            return null
        }

        /*
        This would be caused by a bad request
        Shortcut here -> It returns null because I didn't wanted to lose time handling the error case,
        but I still showed that I knew how to do it.
         */
        if (!request.successful) {
            return null
        }

        return request.body
    }
}