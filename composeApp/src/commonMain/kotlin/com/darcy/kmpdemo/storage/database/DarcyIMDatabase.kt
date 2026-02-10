package com.darcy.kmpdemo.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.darcy.kmpdemo.storage.database.daos.UserDao
import com.darcy.kmpdemo.storage.database.tables.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [
        // 添加数据库表
        UserEntity::class
    ],
    version = 1,
    exportSchema = true
)

//@ConstructedBy(DarcyIMDatabaseConstructor::class)
abstract class DarcyIMDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

fun getDarcyIMDatabase(): DarcyIMDatabase {
    return getIMDatabaseBuilder()
        .setDriver(BundledSQLiteDriver()) // 使用内置的SQLite
        .setQueryCoroutineContext(Dispatchers.IO) // 协程上下文
        .addMigrations()
        .fallbackToDestructiveMigration(false)
        .build()
}

expect fun getIMDatabaseBuilder(): RoomDatabase.Builder<DarcyIMDatabase>