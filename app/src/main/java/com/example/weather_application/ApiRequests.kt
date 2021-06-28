package com.example.weather_application

import com.example.weather_application.api.WeatherJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {
    @GET("data/2.5/weather")
    fun getWeather(@Query("id") id: String,
                   @Query("appid") appid: String,
                   @Query("units") units: String,
                   @Query("lang") lang: String): Call<WeatherJson>
}