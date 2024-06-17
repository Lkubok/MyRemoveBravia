package com.khorzon.mybraviaremote

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

data class PowerParam(
    val status: Boolean
)

data class PowerStateResponseResult(
    val status: String
)

data class SetAudioVolumeParams(
    val volume: String,
    val ui: String = "on",
    val target: String = "speaker"

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
    val method: String = "setPowerStatus",
    val id: Int = 55,
    val params: List<PowerParam>,
    val version: String = "1.0"
)

@Root(name = "Envelope", strict = false)
@Namespace(prefix = "s", reference = "http://schemas.xmlsoap.org/soap/envelope/")
data class Envelope(
    @field:Element(name = "s:Body")
    var body: Body
)

@Root(name = "s:Body", strict = false)
data class Body(
    @field:Element(name = "X_SendIRCC")
    @Namespace(reference = "urn:schemas-sony-com:service:IRCC:1")
    var xSendIRCC: XSendIRCC
)

@Root(name = "X_SendIRCC", strict = false)
@Namespace(prefix = "u", reference = "urn:schemas-sony-com:service:IRCC:1")
data class XSendIRCC(
    @field:Element(name = "IRCCCode")
    var irccCode: String
)

data class SetAudioVolumeRequest(
    val method: String = "setAudioVolume",
    val id: Int = 98,
    val params: List<SetAudioVolumeParams>,
    val version: String = "1.2"
)

data class SetAudioVolumeResponse(
    val result: List<Any>,
    val id: Int
)

data class VolumeInfoResult(

    val volume: Int,
    val minVolume: Int,
    val mute: Boolean,
    val maxVolume: Int,
    val target: String
)

data class GetVolumeInformationResponse(
    val result: List<List<VolumeInfoResult>>,
    val id: Int
)

data class GetVolumeInformationRequest(
    val method: String = "getVolumeInformation",
    val id: Int = 33,
    val params: List<Any> = listOf(),
    val version: String = "1.0"
)

data class ApplicationItem(
    val title: String,
    val uri: String,
    val icon: String,
)

data class GetApplicationListRequest(
    val method: String = "getApplicationList",
    val id: Int = 60,
    val params: List<Any> = listOf(),
    val version: String = "1.0"
)

data class GetApplicationListResponse(
    val result: List<List<ApplicationItem>>,
    val id: Int
)

data class SetActiveAppParam(
    val uri: String
)

data class SetActiveAppRequest(
    val method: String = "setActiveApp",
    val valid: Int = 601,
    val params: List<SetActiveAppParam>,
    val version: String = "1.0"
)

data class SetActiveAppResponse(
    val result: List<Any>,
    val id: Int,
)

data class NetworkSetting(
    val hwAddr: String,
    val netmask: String,
    val ipAddrV4: String,
    val netif: String,
    val ipAddrV6: String,
    val dns: List<String>,
    val gateway: String,
)

data class NetworkInterface(
    val netif: String = "eth0"
)

data class GetNetworkSettingsRequest(
    val method: String = "getNetworkSettings",
    val id: Int = 2,
    val params: List<NetworkInterface>,
    val version: String = "1.0"
)

data class GetNetworkSettingsResponse(
    val result: List<List<NetworkSetting>>,
    val id: Int
)