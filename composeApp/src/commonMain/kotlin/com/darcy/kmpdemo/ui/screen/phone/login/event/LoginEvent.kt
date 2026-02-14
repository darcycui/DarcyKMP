package com.darcy.kmpdemo.ui.screen.phone.login.event

import com.darcy.kmpdemo.ui.base.IEvent

sealed class LoginEvent : IEvent {
    data object LoginSuccessEvent : LoginEvent()
}