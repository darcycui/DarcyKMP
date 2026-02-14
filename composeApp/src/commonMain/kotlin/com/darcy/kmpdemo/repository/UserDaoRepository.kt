package com.darcy.kmpdemo.repository

import com.darcy.kmpdemo.storage.database.daos.UserDao
import com.darcy.kmpdemo.storage.database.getDarcyIMDatabase
import com.darcy.kmpdemo.storage.database.tables.UserEntity
import kotlinx.coroutines.flow.Flow

class UserDaoRepository(
    private val userDao: UserDao = getDarcyIMDatabase().userDao(),
) : IRepository {
    suspend fun insertUser(userEntity: UserEntity) {
        userDao.insert(userEntity)
    }

    suspend fun deleteUser(id: Long) {
        userDao.delete(id)
    }

    suspend fun deleteUser(userEntity: UserEntity) {
        userDao.delete(userEntity)
    }

    suspend fun deleteUserById(id: Long) {
        userDao.delete(UserEntity(userId = id))
    }

    suspend fun updateUser(userEntity: UserEntity) {
        userDao.update(userEntity)
    }

    suspend fun updateUser(id: Long, name: String) {
        userDao.update(id, name)
    }

    suspend fun getUserById(id: Long): UserEntity {
        return userDao.getById(id)?: UserEntity.empty()
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