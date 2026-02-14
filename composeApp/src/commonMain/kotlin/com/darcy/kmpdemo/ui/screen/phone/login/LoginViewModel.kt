package com.darcy.kmpdemo.ui.screen.phone.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.darcy.kmpdemo.bean.http.UsersResponse
import com.darcy.kmpdemo.bean.ui.UserItemBean
import com.darcy.kmpdemo.repository.UserDaoRepository
import com.darcy.kmpdemo.storage.database.tables.UserEntity
import com.darcy.kmpdemo.storage.memory.IMGlobalStorage
import com.darcy.kmpdemo.ui.base.BaseViewModel
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.base.impl.fetch.FetchIntent
import com.darcy.kmpdemo.ui.screen.phone.login.event.LoginEvent
import com.darcy.kmpdemo.ui.screen.phone.login.intent.LoginIntent
import com.darcy.kmpdemo.ui.screen.phone.login.reducer.LoginReducer
import com.darcy.kmpdemo.ui.screen.phone.login.state.LoginState
import kotlin.reflect.KClass

class LoginViewModel(
    private val repository: UserDaoRepository = UserDaoRepository(),
) : BaseViewModel<LoginState>() {
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return LoginViewModel() as T
            }
        }
    }

    override fun initState(): LoginState {
        return LoginState()
    }

    override fun initReducers(): List<IReducer<LoginState>> {
        return listOf(LoginReducer())
    }

    override fun dispatch(intent: IIntent) {
        when (intent) {
            is LoginIntent.ActionRegister -> { // 注册
                actionRegister()
            }

            is LoginIntent.ActionLogin -> { // 登录
                actionLogin(intent.userId, intent.name, intent.password)
            }

            is LoginIntent.ActionAddUser -> { // 添加用户
                actionAddUser(intent.userEntity)
            }

            is LoginIntent.ActionDeleteUser -> { // 删除用户
                actionDeleteUser(intent.userId)
            }

            is LoginIntent.ActionUpdateUser -> { // 更新用户
                actionUpdateUser(intent.userId, intent.name)
            }

            is LoginIntent.ActionQueryUserList -> { // 查询用户列表
                actionQueryUserList()
            }


            else -> {
                super.dispatch(intent)
            }
        }
    }

    private fun actionLogin(userId: Long, name: String, password: String) {
        io {
            // todo 登录逻辑
            IMGlobalStorage.setCurrentUser(
                UserEntity(
                    userId,
                    name,
                    "https://avatars.githubusercontent.com/u/1020407?s=200&v=4"
                )
            )
            sendEvent(LoginEvent.LoginSuccessEvent)
        }
    }

    private fun actionRegister() {
        // todo 注册逻辑
    }


    private fun actionQueryUserList() {
        io {
            val userEntityList = repository.getAllUser().map { item ->
                UserItemBean(
                    id = item.userId,
                    name = item.name,
                    avatar = item.avatar,
                    nickName = item.nickName,
                    age = item.age,
                    sex = item.sex,
                )
            }
            dispatch(FetchIntent.RefreshByFetchData(UsersResponse(userEntityList)))
        }
    }

    private fun actionUpdateUser(id: Long, name: String) {
        io {
            repository.updateUser(id, name)
        }
    }

    private fun actionDeleteUser(id: Long) {
        io {
            repository.deleteUser(id)
        }
    }

    private fun actionAddUser(userEntity: UserEntity) {
        io {
            repository.insertUser(userEntity)
        }
    }
}