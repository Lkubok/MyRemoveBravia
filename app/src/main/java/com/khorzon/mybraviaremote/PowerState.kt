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
