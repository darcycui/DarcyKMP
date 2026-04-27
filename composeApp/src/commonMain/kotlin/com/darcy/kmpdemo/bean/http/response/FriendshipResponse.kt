package com.darcy.kmpdemo.bean.http.response

import kotlinx.serialization.Serializable

@Serializable
data class FriendshipResponse(
    val user: UserResponse = UserResponse(),
    val friend: UserResponse = UserResponse(),
    val alias: String = "",
    val relationStatus: Int = 0,
    val source: String = "",
    val greeting: String = "",
    val addedAt: String = "",
    val isBlocked: Boolean = false,
    val isMuted: Boolean = false,
    val isPinned: Boolean = false,
    val id: Long = 0,
    val createdAt: String = "",
    val updatedAt: String = ""
)