package com.darcy.kmpdemo.bean.http

import com.darcy.kmpdemo.bean.IEntity
import com.darcy.kmpdemo.bean.ui.FriendsItemBean
import com.darcy.kmpdemo.bean.ui.UserItemBean

data class MineResponse(
    val bean: UserItemBean = UserItemBean.empty(),
): IEntity
