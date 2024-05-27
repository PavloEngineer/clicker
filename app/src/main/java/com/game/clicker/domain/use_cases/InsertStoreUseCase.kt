package com.game.clicker.domain.use_cases


import com.game.clicker.domain.models.Store
import com.game.clicker.domain.repositories.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertStoreUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {

    suspend operator fun invoke(store: Store) {
        withContext(Dispatchers.IO) {
            storeRepository.insertStore(store)
        }
    }
}