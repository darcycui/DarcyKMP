package com.darcy.kmpdemo.bean.ui

import com.darcy.kmpdemo.bean.http.base.IUIBean

data class RegisterBean(
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
    val lastActiveTime: String? = null,
    val deletedAt: String? = null,
    val settings: Map<String, String> = emptyMap(),
    val roles: String = "",
    val token: String = "",
) : IUIBean