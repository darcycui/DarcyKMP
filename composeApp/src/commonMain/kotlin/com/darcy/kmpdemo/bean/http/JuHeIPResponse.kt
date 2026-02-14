package com.darcy.kmpdemo.bean.http

import com.darcy.kmpdemo.bean.IEntity
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @OptIn 提示当前非稳定API
@OptIn(InternalSerializationApi::class)
@Serializable
data class JuHeIPResponse(
    @SerialName("City")
    val city: String = "",
    @SerialName("Country")
    val country: String = "",
    @SerialName("District")
    val district: String = "",
    @SerialName("Isp")
    val isp: String = "",
    @SerialName("Province")
    val province: String = "",

    val time: String = "LocalDateTime.now()",
): IEntity {
    override fun toString(): String {
        return "IPEntity(city='$city', country='$country', district='$district', isp='$isp', province='$province', time=$time)"
    }
}