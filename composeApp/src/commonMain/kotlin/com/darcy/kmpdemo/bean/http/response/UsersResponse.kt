package com.darcy.kmpdemo.bean.http.response

import com.darcy.kmpdemo.bean.http.base.IUIBean
import com.darcy.kmpdemo.bean.ui.UserItemBean

data class UsersResponse(
    val items: List<UserItemBean> = emptyList(),
): IUIBean
