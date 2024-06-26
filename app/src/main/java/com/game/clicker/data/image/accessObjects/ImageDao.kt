package com.game.clicker.data.image.accessObjects

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.game.clicker.data.image.entities.ImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Insert
    suspend fun addImage(imageEntity: ImageEntity)

    @Query("SELECT * FROM image")
     fun getAllImages(): Flow<List<ImageEntity>>

    @Query("""
        SELECT * FROM image 
        WHERE imageId NOT IN (
            SELECT imageId FROM store WHERE userId = :userId
        )
    """)
    fun getImagesNotOwnedByUser(userId: Int): Flow<List<ImageEntity>>
}