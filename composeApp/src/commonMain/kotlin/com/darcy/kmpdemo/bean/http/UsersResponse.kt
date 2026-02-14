package com.darcy.kmpdemo.bean.http

import com.darcy.kmpdemo.bean.IEntity
import com.darcy.kmpdemo.bean.ui.UserItemBean

data class UsersResponse(
    val items: List<UserItemBean> = emptyList(),
): IEntity
