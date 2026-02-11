package com.darcy.kmpdemo.storage.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.darcy.kmpdemo.storage.database.tables.ConversationEntity

@Dao
interface ConversationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ConversationEntity)

    @Query("SELECT * FROM ConversationEntity WHERE conversationId=:conversationId")
    suspend fun getConversationById(conversationId: Long): List<ConversationEntity>

    @Query("SELECT * FROM ConversationEntity")
    suspend fun getAllConversations(): List<ConversationEntity>

    @Update
    suspend fun update(item: ConversationEntity)

    @Delete
    suspend fun delete(item: ConversationEntity)
}