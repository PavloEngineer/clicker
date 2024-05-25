package com.game.clicker.data.store.repositories

import com.game.clicker.data.store.accessObjects.StoreDao
import com.game.clicker.data.store.entities.StoreEntity
import com.game.clicker.domain.models.Store
import com.game.clicker.domain.models.StoreWithDetails
import com.game.clicker.domain.repositories.StoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeDao: StoreDao
): StoreRepository {

    override suspend fun insertStore(store: Store) {
        storeDao.insertStore(StoreEntity.toStoreEntity(store))
    }

    override suspend fun getStoresByUserId(userId: Int): Flow<List<StoreWithDetails>> {
        return storeDao.getStoresByUserId(userId).map { list -> list.map { it.toStoreWithDetails() } }
    }
}