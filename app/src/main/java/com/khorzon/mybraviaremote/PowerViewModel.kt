package com.khorzon.mybraviaremote

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PowerViewModel: ViewModel() {

    private val _powerState = mutableStateOf(PowerState())
    val powerState: State<PowerState> = _powerState

    init{
        fetchPowerState()
    }

    fun setIsOn(isOn: Boolean){
        viewModelScope.launch {
            try {
                val setPowerBody = SetPowerStatusRequestBody(params = listOf(PowerParam(isOn)))
                powerStatusService.setPowerStatus(setPowerBody)
            }catch (e:Exception){
                println(e)
            }finally {
                fetchPowerState()
            }
        }
    }

    private fun fetchPowerState(){
        viewModelScope.launch {
            try {
                val powerStateRequestBody = PowerStateRequestBody()
                val powerResponse = powerStatusService.getStatus(powerStateRequestBody)
                println("Response: $powerResponse")
                _powerState.value = _powerState.value.copy(
                    isOn = (powerResponse.result[0].status == "active"),
                    loading = false,
                    error = null
                )
            }catch (e:Exception){
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
