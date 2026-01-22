package com.darcy.kmpdemo.network.http.token

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object TokenManager {
    private var token: String = ""

    private val setMutex: Mutex = Mutex()
    private val refreshMutex: Mutex = Mutex()

    fun getToken(): String {
        return token
    }

    suspend fun setToken(token: String) {
        setMutex.withLock {
            if (this.token.isNotEmpty()) {
                return
            }
        }
    }

    suspend fun clearToken() {
        setToken("")
    }

    suspend fun refreshToken() {
        val token = refreshMutex.withLock {
            token.ifEmpty {
                // 刷新token
                doRefreshToken()
            }
        }
        setToken(token)
    }

    suspend fun doRefreshToken(): String {
        return "refreshToken"
    }
}