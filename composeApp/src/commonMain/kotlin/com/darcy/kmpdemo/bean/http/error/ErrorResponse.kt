package com.darcy.kmpdemo.bean.http.error

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val timestamp: Long = 0,
    var status: Int = 0,
    var error: String = "",
    var message: String = "",
    var path: String = "/",
) {
    companion object {

        val HTTP_REQUEST_ERROR = ErrorResponse(
            status = -1,
            error = "Http Request Error",
            message = "Http请求失败",
            path = "/"
        )

        fun create(
            status: Int = -1,
            error: String = "",
            message: String,
            path: String = "/"
        ): ErrorResponse {
            return ErrorResponse(
                status = status,
                error = error,
                message = "发生错误:$message",
                path = path
            )
        }
    }
}