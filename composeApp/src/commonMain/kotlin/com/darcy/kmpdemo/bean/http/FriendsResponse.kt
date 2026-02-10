package com.darcy.kmpdemo.bean.http

import com.darcy.kmpdemo.bean.IEntity
import com.darcy.kmpdemo.bean.ui.FriendsItemBean

data class FriendsResponse(
    val items: List<FriendsItemBean> = emptyList(),
): IEntity
