package com.darcy.kmpdemo.storage.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.darcy.kmpdemo.storage.database.queryentities.ConversationUsers
import com.darcy.kmpdemo.storage.database.queryentities.UserConversations
import com.darcy.kmpdemo.storage.database.tables.ConversationUserCrossRef

@Dao
interface ConversationUserCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ConversationUserCrossRef)

    @Update
    suspend fun update(item: ConversationUserCrossRef)

    @Delete
    suspend fun delete(item: ConversationUserCrossRef)


    @Transaction
    @Query("SELECT * FROM ConversationEntity WHERE conversationId = :conversationId")
    suspend fun getUsersByConversationId(conversationId: Long): ConversationUsers?

    @Transaction
    @Query("SELECT * FROM UserEntity WHERE userId = :userId")
    suspend fun getConversationsByUserId(userId: Long): UserConversations?
}