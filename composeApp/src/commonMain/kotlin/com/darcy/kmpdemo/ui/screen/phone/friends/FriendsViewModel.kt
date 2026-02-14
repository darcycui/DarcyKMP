package com.darcy.kmpdemo.ui.screen.phone.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.darcy.kmpdemo.bean.http.FriendsResponse
import com.darcy.kmpdemo.bean.ui.FriendsItemBean
import com.darcy.kmpdemo.exception.BaseException
import com.darcy.kmpdemo.repository.FriendshipDaoRepository
import com.darcy.kmpdemo.repository.FriendshipUserCrossRefDaoRepository
import com.darcy.kmpdemo.repository.UserDaoRepository
import com.darcy.kmpdemo.storage.database.tables.FriendshipEntity
import com.darcy.kmpdemo.storage.database.tables.FriendshipUserCrossRef
import com.darcy.kmpdemo.storage.database.tables.FromFriendshipUserCrossRef
import com.darcy.kmpdemo.storage.database.tables.ToFriendshipUserCrossRef
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
    private val userDaoRepository: UserDaoRepository = UserDaoRepository(),
    private val friendshipDaoRepository: FriendshipDaoRepository = FriendshipDaoRepository(),
    private val friendshipUserCrossRefDaoRepository: FriendshipUserCrossRefDaoRepository = FriendshipUserCrossRefDaoRepository(),
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
            is FetchIntent.ActionLoadData -> { // 获取数据
                actionFetchFriendsList()
            }

            is FriendsIntent.ActionAddFriend -> { // 添加好友
//                actionAddFriend(intent.userIdFrom, intent.userIdTo, intent.markName)
                actionAddFriend2(intent.userIdFrom, intent.userIdTo, intent.markName)
            }

            is FriendsIntent.ActionDeleteFriend -> { // 删除好友
                actionDeleteFriend()
            }

            is FriendsIntent.ActionUpdateFriend -> { // 更新好友
                actionUpdateFriend()
            }

            is FriendsIntent.ActionQueryFriendsList -> { // 获取好友列表
//                actionQueryFriendsList(intent.userId)
                actionQueryFriendsList2(intent.userId)
            }

            is PagingIntent.ActionLoadNewPage -> {
                // 分页
            }

            else -> {
                super.dispatch(intent)
            }
        }
    }

    private fun actionQueryFriendsList(userId: Long) {
        io {
            val friends = friendshipUserCrossRefDaoRepository.getFriendsByUserId(userId)
            val uiBeanList: MutableList<FriendsItemBean> = mutableListOf()
            friends.forEach { iFriend ->
                iFriend.getTheFriends().forEach {
                    uiBeanList.add(
                        FriendsItemBean(
                            id = it.userId,
                            name = it.name,
                            nickName = it.nickName,
                            avatar = it.avatar,
                            age = it.age,
                            sex = it.sex
                        )
                    )
                }
            }
            dispatch(FetchIntent.RefreshByFetchData(FriendsResponse(uiBeanList)))
        }
    }

    private fun actionQueryFriendsList2(userId: Long) {
        io {
            val userFriends = friendshipUserCrossRefDaoRepository.getUserFriends(userId)
            val uiBeanList: List<FriendsItemBean> = userFriends.friends.map { item ->
                val userIdSelected =
                    if (item.userIdFrom == userId) item.userIdTo else item.userIdFrom
                val userName = userDaoRepository.getUserById(userIdSelected).name
                val userAvatar = userDaoRepository.getUserById(userIdSelected).avatar
                FriendsItemBean(
                    id = userIdSelected,
                    name = userName,
                    nickName = item.markNameOfTo,
                    avatar = userAvatar,
                )
            }
            dispatch(FetchIntent.RefreshByFetchData(FriendsResponse(uiBeanList)))
        }
    }

    private fun actionUpdateFriend() {


    }

    private fun actionDeleteFriend() {


    }

    private fun actionAddFriend(userIdFrom: Long, userIdTo: Long, markName: String) {
        io {
            friendshipDaoRepository.insert(
                FriendshipEntity(
                    userIdFrom = userIdFrom,
                    userIdTo = userIdTo,
                    markNameOfFrom = "",
                    markNameOfTo = markName,
                )
            )
            friendshipUserCrossRefDaoRepository.insert(
                FromFriendshipUserCrossRef(userIdFrom, userIdTo)
            )
            friendshipUserCrossRefDaoRepository.insert(
                ToFriendshipUserCrossRef(userIdTo, userIdFrom)
            )
        }
    }

    private fun actionAddFriend2(userIdFrom: Long, userIdTo: Long, markName: String) {
        io {
            friendshipDaoRepository.insert(
                FriendshipEntity(
                    userIdFrom = userIdFrom,
                    userIdTo = userIdTo,
                    markNameOfFrom = "",
                    markNameOfTo = markName,
                )
            )
            val friendshipId =
                friendshipDaoRepository.getByUserId(userIdFrom, userIdTo).friendshipId ?: -1L
            friendshipUserCrossRefDaoRepository.insert(
                FriendshipUserCrossRef(friendshipId, userIdFrom)
            )
            friendshipUserCrossRefDaoRepository.insert(
                FriendshipUserCrossRef(friendshipId, userIdTo)
            )
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