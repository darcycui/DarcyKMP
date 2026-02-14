package com.darcy.kmpdemo.storage.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.darcy.kmpdemo.storage.database.tables.FriendshipEntity

@Dao
interface FriendshipUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: FriendshipEntity)

    @Update
    suspend fun update(item: FriendshipEntity)

    @Delete
    suspend fun delete(item: FriendshipEntity)

    @Query("SELECT * FROM FriendshipEntity WHERE friendshipId = :id")
    suspend fun getById(id: Long): FriendshipEntity

}