package com.darcy.kmpdemo.repository

import com.darcy.kmpdemo.storage.database.daos.FriendshipUserDao
import com.darcy.kmpdemo.storage.database.getDarcyIMDatabase
import com.darcy.kmpdemo.storage.database.tables.FriendshipEntity

class FriendshipDaoRepository(
    private val friendshipUserDao: FriendshipUserDao = getDarcyIMDatabase().friendshipDao(),
) : IRepository {
    suspend fun insert(item: FriendshipEntity) {
        friendshipUserDao.insert(item)
    }

    suspend fun update(item: FriendshipEntity) {
        friendshipUserDao.update(item)
    }

    suspend fun delete(item: FriendshipEntity) {
        friendshipUserDao.delete(item)
    }

    suspend fun getByUserId(userIdFrom: Long, userIdTo: Long): FriendshipEntity {
        return friendshipUserDao.getByUserId(userIdFrom, userIdTo)
    }
}