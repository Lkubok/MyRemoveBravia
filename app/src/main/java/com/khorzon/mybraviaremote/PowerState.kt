package com.khorzon.mybraviaremote

data class PowerParam(
    val status: Boolean
)

data class PowerStateResponseResult(
    val status: String
)

data class PowerStateResponse(
    val id: Int,
    val result: List<PowerStateResponseResult>
)

data class PowerStateRequestBody(
    val method: String = "getPowerStatus",
    val id: Int = 50,
    val params: List<String> = emptyList(),
    val version: String = "1.0"
)

data class SetPowerStatusResponse(
    val result: List<String>,
    val id: Int
)

data class SetPowerStatusRequestBody(
    val method:String = "setPowerStatus",
    val id: Int = 55,
    val params: List<PowerParam>,
    val version: String = "1.0"
)