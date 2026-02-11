package com.darcy.kmpdemo.ui.screen.phone.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.darcy.kmpdemo.bean.http.FriendsResponse
import com.darcy.kmpdemo.bean.ui.FriendsItemBean
import com.darcy.kmpdemo.exception.BaseException
import com.darcy.kmpdemo.repository.UserDaoRepository
import com.darcy.kmpdemo.storage.database.tables.UserEntity
import com.darcy.kmpdemo.ui.base.BaseViewModel
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.base.impl.fetch.FetchIntent
import com.darcy.kmpdemo.ui.base.impl.paging.PagingIntent
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenStateIntent
import com.darcy.kmpdemo.ui.screen.phone.friends.intent.FriendsIntent
import com.darcy.kmpdemo.ui.screen.phone.friends.reducer.FriendsReducer
import com.darcy.kmpdemo.ui.screen.phone.friends.state.FriendsState
import com.darcy.kmpdemo.ui.screen.phone.friends.usecase.FetchFriendsListUseCase
import kotlin.reflect.KClass

class FriendsViewModel(
    private val fetchChatListUseCase: FetchFriendsListUseCase = FetchFriendsListUseCase(),
    private val repository: UserDaoRepository = UserDaoRepository()
) : BaseViewModel<FriendsState>() {
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return FriendsViewModel() as T
            }
        }
    }

    override fun initState(): FriendsState {
        return FriendsState()
    }

    override fun initReducers(): List<IReducer<FriendsState>> {
        return listOf(FriendsReducer())
    }

    override fun dispatch(intent: IIntent) {
        when (intent) {
            is FetchIntent.ActionLoadData -> {
                // 获取数据
                actionFetchFriendsList()
            }

            is FriendsIntent.ActionAddFriend -> {
                // 添加好友
                actionAddFriend(intent.userEntity)
            }

            is FriendsIntent.ActionDeleteFriend -> {
                // 删除好友
                actionDeleteFriend(intent.id)
            }

            is FriendsIntent.ActionUpdateFriend -> {
                // 更新好友
                actionUpdateFriend(intent.id, intent.name)
            }

            is FriendsIntent.ActionQueryFriend -> {
                // 查询好友
                actionQueryFriend(intent.id)
            }

            is FriendsIntent.ActionQueryFriendsList -> {
                // 查询好友列表
                actionQueryFriendsList()
            }

            is PagingIntent.ActionLoadNewPage -> {
                // 分页
            }

            else -> {
                super.dispatch(intent)
            }
        }
    }

    private fun actionQueryFriendsList() {
        io {
            val userEntityList = repository.getAllUser().map { item ->
                FriendsItemBean(
                    id = item.userId,
                    name = item.name,
                    avatar = item.avatar,
                    nickName = item.nickName,
                    age = item.age,
                    sex = item.sex,
                )
            }
            dispatch(FetchIntent.RefreshByFetchData(FriendsResponse(userEntityList)))
        }
    }

    private fun actionQueryFriend(id: Long) {
        io {
            val userEntity = repository.getUser(id)
            userEntity?.also {
                val uiBean = FriendsItemBean(
                    id = it.userId,
                    name = it.name,
                    avatar = it.avatar,
                    nickName = it.nickName,
                    age = it.age,
                    sex = it.sex,
                )
                dispatch(FetchIntent.RefreshByFetchData(FriendsResponse(listOf(uiBean))))
            }
        }
    }

    private fun actionUpdateFriend(id: Long, name: String) {
        io {
            repository.updateUser(id, name)
        }
    }

    private fun actionDeleteFriend(id: Long) {
        io {
            repository.deleteUser(id)
        }
    }

    private fun actionAddFriend(userEntity: UserEntity) {
        io {
            repository.insertUser(userEntity)
        }
    }

    private fun actionFetchFriendsList() {
        io {
            dispatch(ScreenStateIntent.ScreenStateChange(ScreenState.Loading))
            val response = fetchChatListUseCase()
//            dispatchFailure(BaseException(404, "加载错误"))
            response.onSuccess {
                dispatch(ScreenStateIntent.ScreenStateChange(ScreenState.Success))
                dispatch(FetchIntent.RefreshByFetchData(it))
//                dispatch(TipsIntent.ShowTips(
//                    title = "提示",
//                    tips = "加载成功",
//                    code = 200,
//                    middleButtonText = "确定",
//                ))
            }.onFailure {
                dispatchFailure(it)
            }
        }
    }

    private fun dispatchFailure(throwable: Throwable) {
        val code = if (throwable is BaseException) throwable.errorCode else -1
        val message = throwable.message ?: "Unknown Error"
        dispatch(ScreenStateIntent.ScreenStateChange(ScreenState.Error(code, message)))
    }
}