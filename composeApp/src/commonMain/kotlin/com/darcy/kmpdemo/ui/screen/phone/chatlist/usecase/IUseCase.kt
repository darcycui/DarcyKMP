package com.darcy.kmpdemo.ui.screen.phone.chatlist.usecase

import com.darcy.kmpdemo.bean.http.ChatListResponse

interface IUseCase<T> {
    suspend operator fun invoke(): Result<T>
}