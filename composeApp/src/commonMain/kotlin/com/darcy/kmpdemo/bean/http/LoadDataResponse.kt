package com.darcy.kmpdemo.bean.http

import kotlinx.serialization.Serializable

@Serializable
data class LoadDataResponse(
    val age: Int = 0,
    val name: String = "",
)
