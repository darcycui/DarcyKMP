package com.darcy.kmpdemo.storage.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.darcy.kmpdemo.storage.database.queryentities.UserFriends
import com.darcy.kmpdemo.storage.database.tables.FriendshipUserCrossRef

@Dao
interface FriendshipUserCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: FriendshipUserCrossRef)

    @Update
    suspend fun update(item: FriendshipUserCrossRef)

    @Delete
    suspend fun delete(item: FriendshipUserCrossRef)


    /**
     * 查询用户所有好友
     */
    @Transaction
    @Query("SELECT * FROM UserEntity  WHERE userId = :userId LIMIT 1")
    suspend fun getUserFriends(userId: Long): UserFriends?

}