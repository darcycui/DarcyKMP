package com.darcy.kmpdemo.bean.websocket.stomp

import com.darcy.kmpdemo.bean.http.response.PrivateMessageResponse
import kotlinx.serialization.Serializable
import kotlin.time.Clock

@Serializable
data class STOMPMessage(
    val senderName: String = "",
    val receiverName: String = "",
    val content: String = "",

    val msgId: String = "",
    val senderId: Long = 0,
    val receiverId: Long = 0,
    val msgType: String = "TEXT",
    val sendTime: String = Clock.System.now().epochSeconds.toString(),
    val isRead: Boolean = false,
    val isRecalled: Boolean = false
)

fun STOMPMessage.toPrivateMessageResponse(): PrivateMessageResponse {
    return PrivateMessageResponse(
        senderId = this.senderId,
        senderName = this.senderName,
        receiverId = this.receiverId,
        receiverName = this.receiverName,
        content = this.content,
        msgId = this.msgId,
        msgType = this.msgType,
        sendTime = this.sendTime,
        isRead = this.isRead,
        isRecalled = this.isRecalled
    )
}