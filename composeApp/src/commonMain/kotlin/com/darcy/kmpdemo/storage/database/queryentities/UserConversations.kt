package com.darcy.kmpdemo.storage.database.queryentities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.darcy.kmpdemo.storage.database.tables.ConversationEntity
import com.darcy.kmpdemo.storage.database.tables.ConversationUserCrossRef
import com.darcy.kmpdemo.storage.database.tables.UserEntity

data class UserConversations(
    @Embedded
    val user: UserEntity,

    @Relation(
        parentColumn = "userId",
        entityColumn = "conversationId",
        associateBy = Junction(ConversationUserCrossRef::class)
    )
    val conversations: List<ConversationEntity>
) {
    companion object {
        fun empty(): UserConversations {
            return UserConversations(
                user = UserEntity.empty(),
                conversations = emptyList()
            )
        }
    }
}
