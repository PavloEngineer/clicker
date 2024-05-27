package com.game.clicker.domain.use_cases

import com.game.clicker.domain.models.Image
import com.game.clicker.domain.repositories.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNotBoughtImagesUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(userId: Int): Flow<List<Image>> {
        return  withContext(Dispatchers.IO) {
            imageRepository.getImagesNotOwnedByUser(userId)
        }
    }
}