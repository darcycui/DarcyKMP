package com.darcy.kmpdemo.ui.screen.phone.add_friend.intent

import com.darcy.kmpdemo.bean.http.response.SearchUserResponse
import com.darcy.kmpdemo.ui.base.IIntent

sealed class AddFriendIntent : IIntent {
    data class ActionSearchUser(
        val phone: String
    ) : AddFriendIntent()

    data class RefreshBySearchUser(
        val response: SearchUserResponse
    ): AddFriendIntent()

    data class ActionApplyFriend(
        val userId: Long
    ) : AddFriendIntent()

    data class RefreshByApplyFriend(
        val response: String
    ): AddFriendIntent()
}