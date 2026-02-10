package com.darcy.kmpdemo.ui.screen.phone.friends.intent

import com.darcy.kmpdemo.storage.database.tables.UserEntity
import com.darcy.kmpdemo.ui.base.IIntent

sealed class FriendsIntent : IIntent {
    data class ActionAddFriend(val userEntity: UserEntity) : FriendsIntent()
    data class ActionDeleteFriend(val id: Long) : FriendsIntent()
    data class ActionUpdateFriend(val id: Long, val name: String) : FriendsIntent()
    data class ActionQueryFriend(val id: Long) : FriendsIntent()
    data object ActionQueryFriendsList : FriendsIntent()
}