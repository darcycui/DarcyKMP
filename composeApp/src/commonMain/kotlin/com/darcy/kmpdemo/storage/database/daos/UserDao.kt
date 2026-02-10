package com.darcy.kmpdemo.storage.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.darcy.kmpdemo.storage.database.tables.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    // 插入
    @Insert
    suspend fun insert(item: UserEntity)

    // 根据id查询
    @Query("SELECT * FROM UserEntity WHERE id = :id")
    suspend fun getById(id: Long): UserEntity?

    // 更新
    @Query("UPDATE UserEntity SET name = :name WHERE id = :id")
    suspend fun update(id: Long, name: String)

    // 删除
    @Query("DELETE FROM UserEntity WHERE id = :id")
    suspend fun delete(id: Long)

    // 查询所有数量
    @Query("SELECT * FROM UserEntity")
    suspend fun getAll(): List<UserEntity>

    // 查询所有
    @Query("SELECT count(*) FROM UserEntity")
    suspend fun count(): Int

    // 查询所有用户 以FLow方式返回
    @Query("SELECT * FROM UserEntity")
    fun getAllAsFlow(): Flow<List<UserEntity>>
}