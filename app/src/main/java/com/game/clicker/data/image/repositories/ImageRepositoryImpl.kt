package com.game.clicker.data.image.repositories

import com.game.clicker.data.image.accessObjects.ImageDao
import com.game.clicker.data.image.entities.ImageEntity
import com.game.clicker.domain.models.Image
import com.game.clicker.domain.repositories.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageDao: ImageDao
): ImageRepository {

    override suspend fun addImage(image: Image) =
        imageDao.addImage(ImageEntity.toImageEntity(image))

    override suspend fun getAllImages(): Flow<List<Image>> =
         imageDao.getAllImages().map { list -> list.map { it.toImage() }}

    override suspend fun getImagesNotOwnedByUser(userId: Int): Flow<List<Image>> =
        imageDao.getImagesNotOwnedByUser(userId).map { list -> list.map { it.toImage() } }


}