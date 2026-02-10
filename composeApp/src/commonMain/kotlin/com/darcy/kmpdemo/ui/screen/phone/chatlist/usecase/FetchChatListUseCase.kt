package com.darcy.kmpdemo.ui.screen.phone.chatlist.usecase

import com.darcy.kmpdemo.bean.http.ChatListResponse
import com.darcy.kmpdemo.repository.ChatListRepository
import com.darcy.kmpdemo.ui.base.IUseCase

class FetchChatListUseCase(
    private val repository: ChatListRepository = ChatListRepository(),
) : IUseCase<ChatListResponse> {
    override suspend fun invoke(): Result<ChatListResponse> {
        val response = repository.getChatList()
        return if (response.items.isNotEmpty()) {
            Result.success(response)
        } else {
            Result.failure(Exception())
        }
    }
}