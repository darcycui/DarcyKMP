package com.darcy.kmpdemo.bean.http.response

import com.darcy.kmpdemo.storage.memory.IMGlobalStorage
import kotlinx.serialization.Serializable
import kotlin.time.Clock

@Serializable
data class PrivateMessageResponse(
    val msgId: String = "",
    val senderId: Long = 0,
    val senderName: String = "",
    val receiverId: Long = 0,
    val receiverName: String = "",
    val content: String = "",
    val msgType: String = "TEXT",
    val sendTime: String = Clock.System.now().epochSeconds.toString(),
    val isRead: Boolean = false,
    val isRecalled: Boolean = false
)

fun PrivateMessageResponse.isSelfSent(): Boolean {
    return this.senderId > 0 && this.senderId == IMGlobalStorage.getCurrentUserId()
}