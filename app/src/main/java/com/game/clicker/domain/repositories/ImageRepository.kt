package com.game.clicker.domain.repositories

import com.game.clicker.domain.models.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun addImage(image: Image)

    suspend fun getAllImages(): Flow<List<Image>>

    suspend fun getImagesNotOwnedByUser(userId: Int): Flow<List<Image>>
}