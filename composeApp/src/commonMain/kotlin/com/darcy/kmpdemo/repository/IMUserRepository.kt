package com.darcy.kmpdemo.repository

import com.darcy.kmpdemo.storage.database.daos.UserDao
import com.darcy.kmpdemo.storage.database.getDarcyIMDatabase
import com.darcy.kmpdemo.storage.database.tables.UserEntity
import kotlinx.coroutines.flow.Flow

class IMUserRepository(
    private val userDao: UserDao = getDarcyIMDatabase().userDao(),
) : IRepository {
    suspend fun insertUser(userEntity: UserEntity) {
        userDao.insert(userEntity)
    }

    suspend fun deleteUser(id: Long) {
        userDao.delete(id)
    }

    suspend fun updateUser(id: Long, name: String) {
        userDao.update(id, name)
    }

    suspend fun getUser(id: Long): UserEntity? {
        return userDao.getById(id)
    }

    suspend fun getUserCount(): Int {
        return userDao.count()
    }

    suspend fun getAllUser(): List<UserEntity> {
        return userDao.getAll()
    }

    suspend fun getAllUserAsFlow(): Flow<List<UserEntity>> {
        return userDao.getAllAsFlow()
    }
}