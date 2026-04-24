package com.darcy.kmpdemo.bean.ui

import com.darcy.kmpdemo.bean.http.base.IUIBean

data class ChatListItemBean(
    val id: Long = -1,
    val title: String = "",
    val subTitle: String = "",
    val avatar: String = "",
) : IUIBean {
    companion object {
        fun empty(): ChatListItemBean {
            return ChatListItemBean()
        }
    }
}