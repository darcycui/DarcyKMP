package com.darcy.kmpdemo.repository

import com.darcy.kmpdemo.storage.database.daos.FriendshipUserCrossRefDao
import com.darcy.kmpdemo.storage.database.getDarcyIMDatabase
import com.darcy.kmpdemo.storage.database.queryentities.IFriends
import com.darcy.kmpdemo.storage.database.tables.FromFriendshipUserCrossRef
import com.darcy.kmpdemo.storage.database.tables.ToFriendshipUserCrossRef

class FriendshipUserCrossRefDaoRepository(
    private val friendshipUserCrossRefDao: FriendshipUserCrossRefDao = getDarcyIMDatabase().friendshipUserCrossRefDao(),
) : IRepository {
    suspend fun insert(item: FromFriendshipUserCrossRef) {
        friendshipUserCrossRefDao.insert(item)
    }

    suspend fun insert(item: ToFriendshipUserCrossRef) {
        friendshipUserCrossRefDao.insert(item)
    }

    suspend fun update(item: FromFriendshipUserCrossRef) {
        friendshipUserCrossRefDao.update(item)
    }

    suspend fun delete(item: FromFriendshipUserCrossRef) {
        friendshipUserCrossRefDao.delete(item)
    }

    suspend fun getFriendsByUserId(userId: Long): List<IFriends> {
        return friendshipUserCrossRefDao.getAllFriends(userId)
    }
}