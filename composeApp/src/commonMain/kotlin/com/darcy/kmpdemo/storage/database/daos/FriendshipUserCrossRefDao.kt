package com.darcy.kmpdemo.storage.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.darcy.kmpdemo.log.logD
import com.darcy.kmpdemo.storage.database.queryentities.FromFriends
import com.darcy.kmpdemo.storage.database.queryentities.IFriends
import com.darcy.kmpdemo.storage.database.queryentities.ToFriends
import com.darcy.kmpdemo.storage.database.tables.FromFriendshipUserCrossRef
import com.darcy.kmpdemo.storage.database.tables.ToFriendshipUserCrossRef
import com.darcy.kmpdemo.storage.database.tables.UserEntity

@Dao
interface FriendshipUserCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: FromFriendshipUserCrossRef)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ToFriendshipUserCrossRef)

    @Update
    suspend fun update(item: FromFriendshipUserCrossRef)

    @Delete
    suspend fun delete(item: FromFriendshipUserCrossRef)

    /**
     * 查询用户作为发起方的所有好友
     */
    @Transaction
    @Query("SELECT * FROM FriendshipEntity  WHERE userIdFrom = :userId LIMIT 1")
    suspend fun getFriendsWhereUserIsFrom(userId: Long): List<FromFriends>?

    /**
     * 查询用户作为接收方的所有好友
     */
    @Transaction
    @Query("SELECT * FROM FriendshipEntity  WHERE userIdTo = :userId LIMIT 1")
    suspend fun getFriendsWhereUserIsTo(userId: Long): List<ToFriends>?

    /**
     * 获取用户的完整好友列表（合并两个方向）
     */
    suspend fun getAllFriends(userId: Long): List<IFriends> {
        val fromFriends = getFriendsWhereUserIsFrom(userId)
        logD("fromFriends:${fromFriends?.size} $fromFriends")
        val toFriends = getFriendsWhereUserIsTo(userId)
        logD("toFriends:${toFriends?.size} $toFriends")

        val result = mutableListOf<IFriends>()

        fromFriends?.forEach { friend ->
            result.add(friend)
        }

        toFriends?.forEach { friend ->
            result.add(friend)
        }

        return result.also {
            logD("all friends:${it.size} $it")
        }
    }

}