package com.darcy.kmpdemo.bean.http.response

import kotlinx.serialization.Serializable

@Serializable
data class ApplyFriendResponse(
    val fromUser: UserResponse = UserResponse(),
    val toUser: UserResponse = UserResponse(),
    val greeting: String = "",
    val remark: String = "",
    /**
     *  PENDING(1),    // 1-待处理
     *  ACCEPTED(2),   // 2-已接受
     *  REJECTED(3),   // 3-已拒绝
     *  IGNORED(4),    // 4-已忽略
     *  EXPIRED(5)     // 5-已过期
     */
    val status: Int = 0,
    val handleTime: String = "",
    val handleResult: String = "",
    val id: Long = 0,
    val createdAt: String = "",
    val updatedAt: String = ""
){

    enum class RequestStatus(val code: Int) {
        PENDING(1),    // 1-待处理
        ACCEPTED(2),   // 2-已接受
        REJECTED(3),   // 3-已拒绝
        IGNORED(4),    // 4-已忽略
        EXPIRED(5)     // 5-已过期
        ;

        companion object {
            fun fromCode(code: Int): RequestStatus {
                return entries.find { it.code == code } ?: PENDING
            }
        }

        fun toCode(): Int {
            return code
        }

    }

}


