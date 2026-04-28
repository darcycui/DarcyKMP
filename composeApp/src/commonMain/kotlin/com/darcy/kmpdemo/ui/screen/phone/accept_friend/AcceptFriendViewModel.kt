package com.darcy.kmpdemo.ui.screen.phone.accept_friend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.darcy.kmpdemo.bean.http.error.toTipsIntent
import com.darcy.kmpdemo.log.logE
import com.darcy.kmpdemo.storage.memory.IMGlobalStorage
import com.darcy.kmpdemo.ui.base.BaseViewModel
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.base.impl.fetch.FetchIntent
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.screen.phone.accept_friend.intent.AcceptFriendIntent
import com.darcy.kmpdemo.ui.screen.phone.accept_friend.reducer.AcceptFriendReducer
import com.darcy.kmpdemo.ui.screen.phone.accept_friend.repository.AcceptFriendRepository
import com.darcy.kmpdemo.ui.screen.phone.accept_friend.state.AcceptFriendState
import kmpdarcydemo.composeapp.generated.resources.Res
import kmpdarcydemo.composeapp.generated.resources.confirm
import org.jetbrains.compose.resources.getString
import kotlin.reflect.KClass

class AcceptFriendViewModel(
    private val acceptFriendRepository: AcceptFriendRepository = AcceptFriendRepository(),
) : BaseViewModel<AcceptFriendState>() {
    companion object {
        val Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return AcceptFriendViewModel() as T
            }
        }
    }
    override fun initState(): AcceptFriendState {
        return AcceptFriendState()
    }

    override fun initReducers(): List<IReducer<AcceptFriendState>> {
        return listOf(AcceptFriendReducer())
    }

    override fun dispatch(intent: IIntent) {
        when (intent) {
            is AcceptFriendIntent.ActionAcceptFriend -> {
                actionAcceptFriend(intent.targetUserId)
            }

            is FetchIntent.ActionFetchData -> {
                actionFetchFriendApplys()
            }

            else -> {
                super.dispatch(intent)
            }
        }
    }

    private fun actionFetchFriendApplys() {
        acceptFriendRepository.fetchFriendApplys(
            IMGlobalStorage.getCurrentUserId(),
            onSuccessList = {
                dispatch(FetchIntent.RefreshByFetchData(it))
            },
            onError = {
                logE("查询申请列表失败：$it")
                main { dispatch(it.toTipsIntent()) }
            })
    }

    private fun actionAcceptFriend(targetUserId: Long) {
        acceptFriendRepository.acceptFriend(
            targetUserId,
            onSuccess = {
                dispatch(AcceptFriendIntent.RefreshByAcceptFriend(it))
            },
            onError = {
                logE("添加失败：$it")
                main { dispatch(it.toTipsIntent()) }
            })
    }
}