package com.darcy.kmpdemo.bean.ui

import com.darcy.kmpdemo.bean.http.base.IUIBean

data class LoginBean(
    val name: String = "",
    val password: String = "",
) : IUIBean