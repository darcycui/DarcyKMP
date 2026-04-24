package com.darcy.kmpdemo.bean.ui

import com.darcy.kmpdemo.bean.http.base.IUIBean

data class AddFriendBean(
    val fromUserId: String = "",
    val toUserId: String = "",
) : IUIBean