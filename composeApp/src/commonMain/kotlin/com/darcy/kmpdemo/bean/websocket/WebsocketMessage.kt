package com.darcy.kmpdemo.bean.websocket

import com.darcy.kmpdemo.bean.http.base.IUIBean
import com.darcy.kmpdemo.platform.TimePlatform
import kotlinx.serialization.Serializable

@Serializable
data class WebsocketMessage(
    var from: String = "",
    var to: String = "",
    var createTime: String? = TimePlatform.getCurrentTimeStamp(),
    var message: String = ""
): IUIBean
