package com.khorzon.mybraviaremote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

private val retrofit = Retrofit.Builder().baseUrl("http://192.168.233.5/sony/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val powerStatusService:ApiService = retrofit.create(ApiService::class.java)

interface ApiService {
    @POST("system")
    suspend fun getStatus(@Body data: PowerStateRequestBody):PowerStateResponse
    @POST("system")
    @Headers("X-Auth-PSK: 123456789")
    suspend fun setPowerStatus(@Body data: SetPowerStatusRequestBody): SetPowerStatusResponse
}