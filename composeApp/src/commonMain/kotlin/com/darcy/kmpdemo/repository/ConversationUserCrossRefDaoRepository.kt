package com.darcy.kmpdemo.repository

import com.darcy.kmpdemo.storage.database.daos.ConversationUserCrossRefDao
import com.darcy.kmpdemo.storage.database.getDarcyIMDatabase
import com.darcy.kmpdemo.storage.database.queryentities.ConversationUsers
import com.darcy.kmpdemo.storage.database.queryentities.UserConversations
import com.darcy.kmpdemo.storage.database.tables.ConversationUserCrossRef

class ConversationUserCrossRefDaoRepository(
    private val conversationUserCrossRefDao: ConversationUserCrossRefDao = getDarcyIMDatabase().conversationUserCrossRefDao(),
) : IRepository {
    suspend fun insert(item: ConversationUserCrossRef) {
        conversationUserCrossRefDao.insert(item)
    }

    suspend fun update(item: ConversationUserCrossRef) {
        conversationUserCrossRefDao.update(item)
    }

    suspend fun delete(item: ConversationUserCrossRef) {
        conversationUserCrossRefDao.delete(item)
    }

    suspend fun deleteByConversationIdUserId(conversationId: Long, userId: Long) {
        conversationUserCrossRefDao.delete(
            ConversationUserCrossRef(
                conversationId = conversationId,
                userId = userId
            )
        )
    }

    suspend fun getUsersByConversationId(conversationId: Long): ConversationUsers {
        return conversationUserCrossRefDao.getUsersByConversationId(conversationId) ?: ConversationUsers.empty()
    }

    suspend fun getConversationsByUserId(userId: Long): UserConversations {
        return conversationUserCrossRefDao.getConversationsByUserId(userId) ?: UserConversations.empty()
    }
}