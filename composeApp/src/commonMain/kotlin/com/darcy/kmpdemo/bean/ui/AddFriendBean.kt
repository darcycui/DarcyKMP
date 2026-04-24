package com.darcy.kmpdemo.bean.ui

import com.darcy.kmpdemo.bean.http.base.IUIBean

data class AddFriendBean(
    val fromUserId: Long = 0,
    val toUserId: Long = 0,
) : IUIBean