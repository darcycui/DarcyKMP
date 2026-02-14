package com.darcy.kmpdemo.storage.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ConversationEntity(
    @PrimaryKey(autoGenerate = true)
    val conversationId: Long? = null,
    val name: String,
    val userIdFrom: Long,
    val userIdTo: Long,
    val type: Int = 0,
    val createdTime: Long = 0,
    val updatedTime: Long = 0,
    val deletedTime: Long = 0
) {
    companion object {
        fun empty(): ConversationEntity {
            return ConversationEntity(
                name = "",
                userIdFrom = 0,
                userIdTo = 0
            )
        }
    }
}
