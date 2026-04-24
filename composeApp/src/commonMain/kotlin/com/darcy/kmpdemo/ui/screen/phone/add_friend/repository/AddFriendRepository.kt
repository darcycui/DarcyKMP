package com.darcy.kmpdemo.ui.screen.phone.add_friend.repository

import com.darcy.kmpdemo.bean.http.error.ErrorResponse
import com.darcy.kmpdemo.bean.http.response.LoginResponse
import com.darcy.kmpdemo.bean.http.response.SearchUserResponse
import com.darcy.kmpdemo.bean.ui.AddFriendBean
import com.darcy.kmpdemo.bean.ui.RegisterBean
import com.darcy.kmpdemo.network.http.HttpManager
import com.darcy.kmpdemo.network.http.urls.Darcy.ADD_FRIEND_URL
import com.darcy.kmpdemo.network.http.urls.Darcy.REGISTER_URL
import com.darcy.kmpdemo.network.http.urls.Darcy.SEARCH_FRIEND_URL
import com.darcy.kmpdemo.repository.IRepository
import kotlinx.serialization.serializer

class AddFriendRepository : IRepository {
    suspend fun searchUser(
        phone: String,
        onSuccess: (SearchUserResponse) -> Unit,
        onError: (ErrorResponse) -> Unit
    ): Unit {
        HttpManager.doPostRequest(
            serializer<SearchUserResponse>(),
            SEARCH_FRIEND_URL,
            mapOf(
                "phone" to phone,
            ),
            needRetry = true,
            needCache = true,
            success = {
                println("success: itClazz=${it.result::class}")
                onSuccess(it.result)
            },
            successList = { },
            errors = {
                println("error: it=$it")
                onError(it)
            })
    }

    suspend fun applyFriend(
        bean: AddFriendBean,
        onSuccess: (LoginResponse) -> Unit,
        onError: (ErrorResponse) -> Unit
    ): Unit {
        HttpManager.doPostRequest(
            serializer<LoginResponse>(),
            ADD_FRIEND_URL,
            mapOf(
                "fromUserId" to bean.fromUserId,
                "toUserId" to bean.toUserId,
            ),
            needRetry = true,
            needCache = true,
            success = {
                println("success: itClazz=${it.result::class}")
                onSuccess(it.result)
            },
            successList = { },
            errors = {
                println("error: it=$it")
                onError(it)
            })
    }

}