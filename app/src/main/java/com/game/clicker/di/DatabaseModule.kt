package com.game.clicker.di

import android.content.Context
import androidx.room.Room
import com.game.clicker.data.database.GameDatabase
import com.game.clicker.data.image.accessObjects.ImageDao
import com.game.clicker.data.image.repositories.ImageRepositoryImpl
import com.game.clicker.data.store.accessObjects.StoreDao
import com.game.clicker.data.store.repositories.StoreRepositoryImpl
import com.game.clicker.data.user.accessObjects.UserDao
import com.game.clicker.data.user.repositories.UserRepositoryImpl
import com.game.clicker.domain.repositories.ImageRepository
import com.game.clicker.domain.repositories.StoreRepository
import com.game.clicker.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val NAME_DATABASE = "game_database"

    @Provides
    @Singleton
    fun provideGameDatabase(@ApplicationContext context: Context): GameDatabase {
        return Room.databaseBuilder(
            context,
            GameDatabase::class.java,
            NAME_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(gameDatabase: GameDatabase): UserDao {
        return gameDatabase.getUserDao()
    }

    @Provides
    @Singleton
    fun provideImageDao(gameDatabase: GameDatabase): ImageDao {
        return gameDatabase.getImageDao()
    }

    @Provides
    @Singleton
    fun provideStoreDao(gameDatabase: GameDatabase): StoreDao {
        return gameDatabase.getStoreDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideImageRepository(imageDao: ImageDao): ImageRepository {
        return ImageRepositoryImpl(imageDao)
    }

    @Provides
    @Singleton
    fun provideStoreRepository(storeDao: StoreDao): StoreRepository {
        return StoreRepositoryImpl(storeDao)
    }
}