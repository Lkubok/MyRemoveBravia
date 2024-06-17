package com.khorzon.mybraviaremote

import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


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

object SoapClient {
    private val baseUrlInterceptor = BaseUrlInterceptor()
    private val okHttpSoapClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original: Request = chain.request()
            val request: Request = original.newBuilder()
                .header("Content-Type", "text/xml; charset=UTF-8")
                .header("SOAPACTION", "\"urn:schemas-sony-com:service:IRCC:1#X_SendIRCC\"")
                .header("X-Auth-PSK", "123456789")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }.addInterceptor(baseUrlInterceptor)
        .build()
    private val retrofitSoap: Retrofit = Retrofit.Builder()
        .baseUrl("http://default.url")
        .client(okHttpSoapClient)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    fun getService(): SoapService {
        return retrofitSoap.create(SoapService::class.java)
    }

    fun setBaseUrl(baseUrl: String) {
        baseUrlInterceptor.baseUrl = baseUrl
    }

}


interface SoapService {
    @POST("sony/ircc")
    fun sendIRCC(@Body request: Envelope): retrofit2.Call<Envelope>
}

interface ApiService {
    @POST("sony/system")
    suspend fun getStatus(@Body data: PowerStateRequestBody): PowerStateResponse

    @POST("sony/system")
    @Headers("X-Auth-PSK: 123456789")
    suspend fun setPowerStatus(
        @Body data: SetPowerStatusRequestBody,
    ): SetPowerStatusResponse

    @POST("sony/audio")
    @Headers("X-Auth-PSK: 123456789")
    suspend fun setAudioVolume(@Body data: SetAudioVolumeRequest): SetAudioVolumeResponse

    @POST("sony/audio")
    @Headers("X-Auth-PSK: 123456789")
    suspend fun getVolumeInformation(@Body data: GetVolumeInformationRequest = GetVolumeInformationRequest() ): GetVolumeInformationResponse

    @POST("sony/system")
    @Headers("X-Auth-PSK: 123456789")
    suspend fun getNetworkSettings(@Body data: GetNetworkSettingsRequest ): GetNetworkSettingsResponse

    @POST("sony/appControl")
    @Headers("X-Auth-PSK: 123456789")
    suspend fun getApplicationList(@Body data: GetApplicationListRequest ): GetApplicationListResponse

    @POST("sony/appControl")
    @Headers("X-Auth-PSK: 123456789")
    suspend fun setActiveApp(@Body data: SetActiveAppRequest ): SetActiveAppResponse
}