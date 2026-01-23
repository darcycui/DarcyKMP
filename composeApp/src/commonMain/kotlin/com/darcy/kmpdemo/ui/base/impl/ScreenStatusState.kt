package com.darcy.kmpdemo.ui.base.impl

import com.darcy.kmpdemo.ui.base.IState
import com.darcy.kmpdemo.ui.base.ScreenStatus

open class ScreenStatusState(
    var screenState: ScreenStatus = ScreenStatus.Initial,
) : IState {
    open fun copys(screenState: ScreenStatus):ScreenStatusState {
        return ScreenStatusState(screenState)
    }

}