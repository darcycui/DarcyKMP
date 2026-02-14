package com.darcy.kmpdemo.ui.screen.phone.friends.intent

import com.darcy.kmpdemo.ui.base.IIntent

sealed class FriendsIntent : IIntent {

    data class ActionAddFriend(
        val userIdFrom: Long,
        val userIdTo: Long,
        val markName: String
    ) : FriendsIntent()

    data class ActionDeleteFriend(val userId: Long, val friendUserId: Long) : FriendsIntent()
    data class ActionUpdateFriend(
        val userId: Long,
        val friendUserId: Long,
        val markName: String
    ) : FriendsIntent()

    data class ActionQueryFriendsList(val userId: Long) : FriendsIntent()
}