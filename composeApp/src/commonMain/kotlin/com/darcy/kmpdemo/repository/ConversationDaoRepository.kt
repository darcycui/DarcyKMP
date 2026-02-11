package com.darcy.kmpdemo.repository

import com.darcy.kmpdemo.storage.database.daos.ConversationDao
import com.darcy.kmpdemo.storage.database.getDarcyIMDatabase
import com.darcy.kmpdemo.storage.database.tables.ConversationEntity

class ConversationDaoRepository(
    private val conversationDao: ConversationDao = getDarcyIMDatabase().conversationDao(),
) : IRepository {
    suspend fun createConversation(item: ConversationEntity) {
        // 创建会话
        conversationDao.insert(item)
    }

    suspend fun getConversationById(conversationId: Long): List<ConversationEntity> {
        // 获取会话
        return conversationDao.getConversationById(conversationId)
    }

    suspend fun getAllConversations(): List<ConversationEntity> {
        // 获取所有会话
        return conversationDao.getAllConversations()
    }

    suspend fun updateConversation(item: ConversationEntity) {
        // 更新会话
        conversationDao.update(item)
    }

    suspend fun deleteConversation(item: ConversationEntity) {
        // 删除会话
        conversationDao.delete(item)
    }

    suspend fun deleteConversationById(conversationId: Long) {
        // 删除会话
        conversationDao.delete(ConversationEntity(conversationId = conversationId))
    }
}