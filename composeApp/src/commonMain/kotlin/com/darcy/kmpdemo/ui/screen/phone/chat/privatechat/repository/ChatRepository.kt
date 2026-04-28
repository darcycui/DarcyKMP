package com.darcy.kmpdemo.ui.screen.phone.chat.privatechat.repository

import com.darcy.kmpdemo.bean.http.error.ErrorResponse
import com.darcy.kmpdemo.bean.http.response.PrivateMessageResponse
import com.darcy.kmpdemo.bean.http.response.PrivateMessageResponsePage
import com.darcy.kmpdemo.network.http.HttpManager
import com.darcy.kmpdemo.network.http.urls.Darcy.QUERY_PRIVATE_MESSAGE_URL
import com.darcy.kmpdemo.repository.IRepository
import kotlinx.serialization.serializer

class ChatRepository : IRepository {

    fun fetchMessages(
        userId: Long,
        conversationId: Long,
        page: Int,
        size: Int,
        onSuccess: (PrivateMessageResponsePage) -> Unit,
        onError: (ErrorResponse) -> Unit
    ): Unit {
        HttpManager.doPostRequest(
            serializer<PrivateMessageResponsePage>(),
            QUERY_PRIVATE_MESSAGE_URL,
            mapOf(
                "conversationId" to conversationId.toString(),
                "page" to page.toString(),
                "size" to size.toString(),
            ),
            needRetry = true,
            needCache = true,
            success = {
                println("success: itClazz=${it.result::class}")
                onSuccess(it.result)
            },
            successList = {},
            errors = {
                println("error: it=$it")
                onError(it)
            })
    }
}