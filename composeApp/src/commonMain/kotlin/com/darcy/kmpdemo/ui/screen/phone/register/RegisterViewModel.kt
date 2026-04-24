package com.darcy.kmpdemo.ui.screen.phone.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.darcy.kmpdemo.log.logE
import com.darcy.kmpdemo.storage.memory.IMGlobalStorage
import com.darcy.kmpdemo.ui.base.BaseViewModel
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.screen.phone.login.event.LoginEvent
import com.darcy.kmpdemo.ui.screen.phone.register.event.RegisterEvent
import com.darcy.kmpdemo.ui.screen.phone.register.intent.RegisterIntent
import com.darcy.kmpdemo.ui.screen.phone.register.reducer.RegisterReducer
import com.darcy.kmpdemo.ui.screen.phone.register.repository.RegisterRepository
import com.darcy.kmpdemo.ui.screen.phone.register.state.RegisterState
import kmpdarcydemo.composeapp.generated.resources.Res
import kmpdarcydemo.composeapp.generated.resources.confirm
import org.jetbrains.compose.resources.getString
import kotlin.reflect.KClass

class RegisterViewModel(
    private val registerRepository: RegisterRepository = RegisterRepository(),
) : BaseViewModel<RegisterState>() {
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return RegisterViewModel() as T
            }
        }
    }
    override fun initState(): RegisterState {
        return RegisterState()
    }

    override fun initReducers(): List<IReducer<RegisterState>> {
        return listOf(RegisterReducer())
    }

    override fun dispatch(intent: IIntent) {
        when (intent) {
            is RegisterIntent.ActionRegister -> {
                registerAction(intent)
            }

            else -> super.dispatch(intent)
        }
    }

    private fun registerAction(intent: RegisterIntent.ActionRegister) {
        io {
            registerRepository.register(
                intent.bean,
                onSuccess = {
                    io {
                        IMGlobalStorage.setCurrentUser(it)
                        sendEvent(RegisterEvent.RegisterSuccessEvent)
                    }
                },
                onError = {
                    logE("注册失败：$it")
                    main {
                        dispatch(
                            TipsIntent.ShowTips(
                                title = "注册失败",
                                tips = it.message,
                                code = it.status,
                                middleButtonText = getString(Res.string.confirm),
                            )
                        )
                    }
                }
            )
        }
    }
}