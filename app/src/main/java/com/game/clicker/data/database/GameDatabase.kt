package com.game.clicker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.game.clicker.data.image.accessObjects.ImageDao
import com.game.clicker.data.image.entities.ImageEntity
import com.game.clicker.data.store.accessObjects.StoreDao
import com.game.clicker.data.store.entities.StoreEntity
import com.game.clicker.data.user.accessObjects.UserDao
import com.game.clicker.data.user.entities.UserEntity
import com.game.clicker.data.utils.Converters

@Database(
    entities = [UserEntity::class, ImageEntity::class, StoreEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class GameDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getImageDao(): ImageDao

    abstract fun getStoreDao(): StoreDao
}