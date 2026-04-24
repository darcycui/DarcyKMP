package com.darcy.kmpdemo.bean.http.response

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val username: String = "",
    val passwordHash: String = "",
    val nickname: String = "",
    val avatar: String = "",
    val phone: String = "",
    val email: String = "",
    val gender: String = "",
    val age: Int = 0,
    val signature: String = "",
    val status: Int = 0,
    val onlineStatus: Int = 0,
    val lastActiveTime: String = "",
    val deletedAt: String = "",
    val settings: Map<String, String> = emptyMap(),
    val roles: String = "",
    val token: String = "",
    val id: Long = 0,
    val createdAt: String = "",
    val updatedAt: String = ""
) {
    companion object {
        fun empty(): RegisterResponse {
            return RegisterResponse()
        }
    }
}