package com.darcy.kmpdemo.bean.http.response

import com.darcy.kmpdemo.bean.http.base.IUIBean
import com.darcy.kmpdemo.bean.ui.UserItemBean

data class MineResponse(
    val bean: UserItemBean = UserItemBean.empty(),
): IUIBean
