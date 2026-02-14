package com.darcy.kmpdemo.storage.database.queryentities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.darcy.kmpdemo.storage.database.tables.FriendshipEntity
import com.darcy.kmpdemo.storage.database.tables.FromFriendshipUserCrossRef
import com.darcy.kmpdemo.storage.database.tables.ToFriendshipUserCrossRef
import com.darcy.kmpdemo.storage.database.tables.UserEntity

interface IFriends {
    fun getTheFriendship(): FriendshipEntity
    fun getTheFriends(): List<UserEntity>
}

// 处理用户作为好友关系发起方(userIdFrom)的情况
data class FromFriends(
    @Embedded
    val friendship: FriendshipEntity,

    @Relation(
        parentColumn = "userIdFrom",
        entityColumn = "userId",
        associateBy = Junction(FromFriendshipUserCrossRef::class)
    )
    val friends: List<UserEntity>
) : IFriends {
    override fun getTheFriendship(): FriendshipEntity {
        return friendship
    }

    override fun getTheFriends(): List<UserEntity> {
        return friends
    }
}

// 处理用户作为好友关系接收方(userIdTo)的情况
data class ToFriends(
    @Embedded
    val friendship: FriendshipEntity,

    @Relation(
        parentColumn = "userIdTo",
        entityColumn = "userId",
        associateBy = Junction(ToFriendshipUserCrossRef::class)
    )
    val friends: List<UserEntity>
) : IFriends {

    override fun getTheFriendship(): FriendshipEntity {
        return friendship
    }

    override fun getTheFriends(): List<UserEntity> {
        return friends
    }
}