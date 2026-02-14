package com.darcy.kmpdemo.storage.database.tables

import androidx.room.Entity

// 交叉引用类，用于建立用户和好友关系之间的关联
@Entity(primaryKeys = ["userIdFrom", "userId"])
data class FromFriendshipUserCrossRef(
    val userIdFrom: Long,
    val userId: Long,
)
