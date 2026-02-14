package com.darcy.kmpdemo.ui.screen.phone.mine.event

import com.darcy.kmpdemo.ui.base.IEvent

sealed class MineEvent: IEvent {
    data object ActionLogout: MineEvent()
}