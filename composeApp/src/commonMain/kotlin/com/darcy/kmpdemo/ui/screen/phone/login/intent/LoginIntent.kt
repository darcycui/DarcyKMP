package com.darcy.kmpdemo.ui.screen.phone.login.intent

import com.darcy.kmpdemo.storage.database.tables.UserEntity
import com.darcy.kmpdemo.ui.base.IIntent

sealed class LoginIntent : IIntent {
    data class ActionRegister(val name: String, val password: String) : LoginIntent()
    data class ActionLogin(
        val userEntity: UserEntity
    ) : LoginIntent()

    data class ActionAddUser(val userEntity: UserEntity) : LoginIntent()
    data class ActionDeleteUser(val userId: Long) : LoginIntent()
    data class ActionUpdateUser(val userId: Long, val name: String) : LoginIntent()
    data object ActionQueryUserList : LoginIntent()
}