package com.khorzon.mybraviaremote

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PowerViewModel : ViewModel() {

    private val _powerState = mutableStateOf(PowerState())
    val powerState: State<PowerState> = _powerState
    private val _volumeState = mutableIntStateOf(0)
    val volumeState: State<Int> = _volumeState
    private val _networkSettings = mutableStateOf<List<NetworkSetting>>(listOf())
    val networkSettings: State<List<NetworkSetting>> = _networkSettings
    private val _apps = mutableStateOf<List<ApplicationItem>>(listOf())
    val apps: State<List<ApplicationItem>> = _apps

    private val _sliderPosition = mutableFloatStateOf(0f)
    val sliderPosition:State<Float> = _sliderPosition

    private val retrofitService = RetrofitClient.getService()
    private val soapService = SoapClient.getService()
    private var debounceJob: Job? = null

    init {
        fetchPowerState()
        fetchVolumeState()
    }

    private fun fetchVolumeState() {
        viewModelScope.launch {
            try {
                val volResponse = retrofitService.getVolumeInformation()
                _volumeState.intValue = volResponse.result[0][0].volume
                _sliderPosition.floatValue = volResponse.result[0][0].volume.toFloat()
            } catch (e: Exception) {
                println(e)
                Log.d("REQUEST ERROR", e.message.toString())
            }
        }
    }

    fun setIsOn(isOn: Boolean) {
        viewModelScope.launch {
            try {
                val setPowerBody = SetPowerStatusRequestBody(params = listOf(PowerParam(isOn)))
                retrofitService.setPowerStatus(setPowerBody)
            } catch (e: Exception) {
                println(e)
                Log.d("REQUEST ERROR", e.message.toString())
            } finally {
                fetchPowerState()
            }
        }
    }

    fun setVolume(newVol: Int) {
        _sliderPosition.floatValue =newVol.toFloat()
        debounceJob?.cancel()
        debounceJob = CoroutineScope(Dispatchers.Main).launch {
            delay(100)
            try {
                val volumeBody =
                    SetAudioVolumeRequest(params = listOf(SetAudioVolumeParams(newVol.toString())))
                retrofitService.setAudioVolume(volumeBody)
                _volumeState.value = newVol
            } catch (e: Exception) {
                println(e)
                Log.d("REQUEST ERROR", e.message.toString())
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
                    } else {
                        println("Error: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: retrofit2.Call<Envelope>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    private fun fetchPowerState() {
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
                Log.d("REQUEST ERROR", e.message.toString())
            }
        }
    }

    data class PowerState(
        val loading: Boolean = true,
        val isOn: Boolean? = null,
        val error: String? = null
    )
}
