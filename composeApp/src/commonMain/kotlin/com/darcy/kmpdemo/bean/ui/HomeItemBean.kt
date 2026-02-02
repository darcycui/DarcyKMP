package com.darcy.kmpdemo.bean.ui

import com.darcy.kmpdemo.bean.IEntity
import com.darcy.kmpdemo.ui.screen.learn.navigation.LearnPages

data class HomeItemBean(
    val id: Int = -1,
    var title: String = "",
    val learnPages: LearnPages,
) : IEntity