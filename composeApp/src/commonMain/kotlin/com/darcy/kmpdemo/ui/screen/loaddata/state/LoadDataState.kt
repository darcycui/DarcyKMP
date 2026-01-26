package com.darcy.kmpdemo.ui.screen.loaddata.state

import com.darcy.kmpdemo.ui.base.impl.ScreenStatusState

data class LoadDataState(
    val content: String = "default"
) : ScreenStatusState() {
}