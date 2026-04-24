package com.darcy.kmpdemo.bean.http.response

import com.darcy.kmpdemo.bean.http.base.IResponse
import com.darcy.kmpdemo.bean.http.base.IUIBean
import com.darcy.kmpdemo.bean.ui.ChatListItemBean

data class ChatListResponse(
    val items: List<ChatListItemBean> = emptyList(),
): IResponse
