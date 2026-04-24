package com.darcy.kmpdemo.bean.http.response

import com.darcy.kmpdemo.bean.http.base.IUIBean
import com.darcy.kmpdemo.bean.ui.FriendsItemBean

data class FriendsResponse(
    val items: List<FriendsItemBean> = emptyList(),
): IUIBean
