package com.darcy.kmpdemo.ui.screen.phone.add_friend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.darcy.kmpdemo.ui.base.BaseViewModel
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.screen.phone.add_friend.intent.AddFriendIntent
import com.darcy.kmpdemo.ui.screen.phone.add_friend.reducer.AddFriendReducer
import com.darcy.kmpdemo.ui.screen.phone.add_friend.repository.AddFriendRepository
import com.darcy.kmpdemo.ui.screen.phone.add_friend.state.AddFriendState
import kotlin.reflect.KClass

class AddFriendViewModel(
    private val repository: AddFriendRepository = AddFriendRepository(),
) : BaseViewModel<AddFriendState>() {
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return AddFriendViewModel() as T
            }
        }
    }
    override fun initState(): AddFriendState {
        return AddFriendState()
    }

    override fun initReducers(): List<IReducer<AddFriendState>> {
        return listOf(AddFriendReducer())
    }

    override fun dispatch(intent: IIntent) {
        when (intent) {
            is AddFriendIntent.ActionSearchUser -> {
                actionSearchUser(intent.phone)
            }
            is AddFriendIntent.ActionApplyFriend -> {
                actionApplyFriend(intent.userId)
            }

            else -> {
                super.dispatch(intent)

            }
        }
    }

    private fun actionApplyFriend(userId: Long) {
        io {
            // todo apply friend
        }
    }

    private fun actionSearchUser(phone: String) {
        io {
            // todo search user
        }
    }
}