package com.darcy.kmpdemo.bean.http

import com.darcy.kmpdemo.bean.IEntity
import com.darcy.kmpdemo.bean.ui.ChatListItemBean

data class ChatListResponse(
    val items: List<ChatListItemBean> = emptyList(),
): IEntity
