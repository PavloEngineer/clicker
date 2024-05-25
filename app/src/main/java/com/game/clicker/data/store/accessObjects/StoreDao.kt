package com.game.clicker.data.store.accessObjects

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.game.clicker.data.store.StoreWithDetailsEntity
import com.game.clicker.data.store.entities.StoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {
    @Insert
    suspend fun insertStore(store: StoreEntity)

    @Transaction
    @Query("SELECT * FROM store WHERE userId = :userId")
     fun getStoresByUserId(userId: Int): Flow<List<StoreWithDetailsEntity>>
}