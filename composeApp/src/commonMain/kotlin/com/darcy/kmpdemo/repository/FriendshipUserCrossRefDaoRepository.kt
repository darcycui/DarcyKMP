package com.darcy.kmpdemo.repository

import com.darcy.kmpdemo.storage.database.daos.FriendshipUserCrossRefDao
import com.darcy.kmpdemo.storage.database.getDarcyIMDatabase
import com.darcy.kmpdemo.storage.database.queryentities.UserFriends
import com.darcy.kmpdemo.storage.database.tables.FriendshipUserCrossRef

class FriendshipUserCrossRefDaoRepository(
    private val friendshipUserCrossRefDao: FriendshipUserCrossRefDao = getDarcyIMDatabase().friendshipUserCrossRefDao(),
) : IRepository {
    suspend fun insert(item: FriendshipUserCrossRef) {
        friendshipUserCrossRefDao.insert(item)
    }

    suspend fun update(item: FriendshipUserCrossRef) {
        friendshipUserCrossRefDao.update(item)
    }

    suspend fun delete(item: FriendshipUserCrossRef) {
        friendshipUserCrossRefDao.delete(item)
    }

    suspend fun getUserFriends(userId: Long): UserFriends {
        return friendshipUserCrossRefDao.getUserFriends(userId) ?: UserFriends.empty()
    }
}