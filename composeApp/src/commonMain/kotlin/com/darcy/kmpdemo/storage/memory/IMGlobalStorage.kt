package com.darcy.kmpdemo.storage.memory

import com.darcy.kmpdemo.storage.database.tables.UserEntity
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object IMGlobalStorage {
    private var currentUser: UserEntity = UserEntity.empty()

    // kmp 同步代码 使用协程锁
    private val mutex: Mutex = Mutex()

    fun getCurrentUser(): UserEntity {
        return currentUser
    }

    fun getCurrentUserId(): Long {
        return currentUser.userId
    }

    suspend fun setCurrentUser(user: UserEntity) {
        mutex.withLock {
            currentUser = user
        }
    }

}