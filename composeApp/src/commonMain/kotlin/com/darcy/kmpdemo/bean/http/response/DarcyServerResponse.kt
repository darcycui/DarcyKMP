package com.darcy.kmpdemo.bean.http.response

import com.darcy.kmpdemo.bean.http.base.IUIBean
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

// @OptIn 提示当前非稳定API
@OptIn(InternalSerializationApi::class)
@Serializable
data class DarcyServerResponse(
    val id: Long = -1L,
    val name: String = "",
) : IUIBean {
    override fun toString(): String {
        return "UserEntity(id=$id, name='$name')"
    }
}