package com.darcy.kmpdemo.bean.ui

import com.darcy.kmpdemo.bean.IEntity

data class ChatListItemBean(
    val id: Long = -1,
    val title: String = "",
    val subTitle: String = "",
    val avatar: String = "",
) : IEntity