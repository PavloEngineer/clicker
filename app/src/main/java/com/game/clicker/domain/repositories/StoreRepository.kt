package com.game.clicker.domain.repositories

import com.game.clicker.domain.models.Store
import com.game.clicker.domain.models.StoreWithDetails
import kotlinx.coroutines.flow.Flow

interface StoreRepository {

    suspend fun insertStore(store: Store)

    suspend fun getStoresByUserId(userId: Int): Flow<List<StoreWithDetails>>
}