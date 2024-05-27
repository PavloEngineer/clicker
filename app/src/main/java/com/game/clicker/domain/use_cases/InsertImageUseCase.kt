package com.game.clicker.domain.use_cases

import com.game.clicker.domain.models.Image
import com.game.clicker.domain.repositories.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {

    suspend operator fun invoke(image: Image) {
        withContext(Dispatchers.IO) {
            imageRepository.addImage(image)
        }
    }
}