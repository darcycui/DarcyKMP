package com.darcy.kmpdemo.ui.screen.phone.register.event

import com.darcy.kmpdemo.ui.base.IEvent

sealed class RegisterEvent : IEvent {
    data object RegisterSuccessEvent : RegisterEvent()
}