package com.darcy.kmpdemo.bean.ui

import com.darcy.kmpdemo.bean.http.base.IUIBean

data class LoginBean(
    val username: String = "",
    val password: String = "",
    val phone: String = "",
    val email: String = "",
) : IUIBean