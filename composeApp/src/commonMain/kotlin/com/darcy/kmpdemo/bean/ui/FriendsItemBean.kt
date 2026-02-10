package com.darcy.kmpdemo.bean.ui

import com.darcy.kmpdemo.bean.IEntity

data class FriendsItemBean(
    val id: Long = -1,
    val name: String = "",
    val nickName: String = "",
    val avatar: String = "",
    val age: Int = 0,
    val sex: Int = 0,
) : IEntity