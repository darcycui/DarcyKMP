package com.darcy.kmpdemo.storage.database.tables

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["userIdFrom", "userIdTo"],
)
data class FriendshipEntity(
    val userIdFrom: Long,
    val userIdTo: Long,
    val markNameOfTo: String,
    val markNameOfFrom: String,
    val createdTime: Long = 0,
    val updatedTime: Long = 0,
    val deletedTime: Long = 0,
    val friendshipId: String = "$userIdFrom-$userIdTo",
) {
    companion object {
        fun empty(): FriendshipEntity {
            return FriendshipEntity(
                userIdFrom = 0,
                userIdTo = 0,
                markNameOfTo = "",
                markNameOfFrom = ""
            )
        }
    }
}
