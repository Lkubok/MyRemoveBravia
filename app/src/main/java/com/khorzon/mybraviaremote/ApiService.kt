package com.khorzon.mybraviaremote

import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url


class BaseUrlInterceptor : Interceptor {
    @Volatile
    var baseUrl: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        baseUrl?.let {
            val newUrl = request.url.newBuilder()
                .scheme(it.toHttpUrlOrNull()!!.scheme)
                .host(it.toHttpUrlOrNull()!!.host)
                .port(it.toHttpUrlOrNull()!!.port)
                .build()
            request = request.newBuilder().url(newUrl).build()
        }
        return chain.proceed(request)
    }
}

object RetrofitClient {
    private val baseUrlInterceptor = BaseUrlInterceptor()

    private val client = OkHttpClient.Builder()
        .addInterceptor(baseUrlInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://default.url/") // This will be overridden by the interceptor
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    fun setBaseUrl(baseUrl: String) {
        baseUrlInterceptor.baseUrl = baseUrl
    }
}


//private val retrofit = Retrofit.Builder().baseUrl("http://192.168.233.5/sony/")
//    .addConverterFactory(GsonConverterFactory.create()).build()
//
//val powerStatusService:ApiService = retrofit.create(ApiService::class.java)

interface ApiService {
    @POST("sony/system")
    suspend fun getStatus(@Body data: PowerStateRequestBody): PowerStateResponse

    @POST("sony/system")
    @Headers("X-Auth-PSK: 123456789")
    suspend fun setPowerStatus(
        @Body data: SetPowerStatusRequestBody,
    ): SetPowerStatusResponse
}