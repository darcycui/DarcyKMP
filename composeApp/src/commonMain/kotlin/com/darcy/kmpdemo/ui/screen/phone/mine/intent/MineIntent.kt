package com.darcy.kmpdemo.ui.screen.phone.mine.intent

import com.darcy.kmpdemo.ui.base.IIntent

sealed class MineIntent : IIntent {
    data object ActionLogout : MineIntent()
    data class RefreshAvatar(val avatarPath: String) : MineIntent()
    data object ActionPickImage : MineIntent()
}