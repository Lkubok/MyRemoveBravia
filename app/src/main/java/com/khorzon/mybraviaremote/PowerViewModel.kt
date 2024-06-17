package com.khorzon.mybraviaremote

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PowerViewModel : ViewModel() {

    private val _powerState = mutableStateOf(PowerState())
    val powerState: State<PowerState> = _powerState

    private val retrofitService = RetrofitClient.getService()
    private val soapService = SoapClient.getService()

    init {
        fetchPowerState()
    }

    fun setIsOn(isOn: Boolean) {
        viewModelScope.launch {
            try {
                val setPowerBody = SetPowerStatusRequestBody(params = listOf(PowerParam(isOn)))
                retrofitService.setPowerStatus(setPowerBody)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun sendSoapRequest(irccCode: String) {
        viewModelScope.launch {

            val xSendIRCC = XSendIRCC(irccCode = irccCode)
            val body = Body(xSendIRCC)
            val envelope = Envelope(body)
            val call = soapService.sendIRCC(envelope)
            call.enqueue(object : retrofit2.Callback<Envelope> {
                override fun onResponse(
                    call: retrofit2.Call<Envelope>,
                    response: retrofit2.Response<Envelope>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        println("Success: $responseBody")
                    } else { println("Error: ${response.errorBody()?.string()}") }
                }

                override fun onFailure(call: retrofit2.Call<Envelope>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    fun fetchPowerState() {
        viewModelScope.launch {
            try {
                val powerStateRequestBody = PowerStateRequestBody()
                val powerResponse = retrofitService.getStatus(powerStateRequestBody)
                println("Response: $powerResponse")
                _powerState.value = _powerState.value.copy(
                    isOn = (powerResponse.result[0].status == "active"),
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _powerState.value = _powerState.value.copy(
                    loading = false,
                    error = "Error occurred: ${e.message}"
                )

            }
        }
    }

    data class PowerState(
        val loading: Boolean = true,
        val isOn: Boolean? = null,
        val error: String? = null
    )
}
