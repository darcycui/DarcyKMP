package com.darcy.kmpdemo.ui.screen.phone.register.intent

import com.darcy.kmpdemo.bean.ui.RegisterBean
import com.darcy.kmpdemo.ui.base.IIntent

sealed class RegisterIntent : IIntent {
    data class ActionRegister(
        val bean: RegisterBean
    ) : RegisterIntent()
}