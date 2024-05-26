package com.game.clicker.domain.use_cases

import com.game.clicker.domain.models.StoreWithDetails
import com.game.clicker.domain.repositories.StoreRepository
import com.game.clicker.domain.utils.TypeImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllBackgroundForUserUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {
    suspend operator fun invoke(userId: Int): Flow<List<StoreWithDetails>> {
        return withContext(Dispatchers.IO) {
            storeRepository.getStoresByUserId(userId)
                .map { list -> list.filter { it.image.typeImage == TypeImage.BACKGROUND } }
        }
    }
}