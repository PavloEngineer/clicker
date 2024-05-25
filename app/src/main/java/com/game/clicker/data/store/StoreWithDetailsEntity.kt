package com.game.clicker.data.store

import androidx.room.Embedded
import androidx.room.Relation
import com.game.clicker.data.image.entities.ImageEntity
import com.game.clicker.data.store.entities.StoreEntity
import com.game.clicker.data.user.entities.UserEntity
import com.game.clicker.domain.models.Image
import com.game.clicker.domain.models.StoreWithDetails

data class StoreWithDetailsEntity(
    @Embedded val store: StoreEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val user: UserEntity,

    @Relation(
        parentColumn = "imageId",
        entityColumn = "imageId"
    )
    val image: ImageEntity
) {
    fun toStoreWithDetails(): StoreWithDetails {
        return StoreWithDetails(store.toStore(), user.toUser(), image.toImage())
    }

    companion object {
        fun toStoreWithDetailsEntity(storeWithDetails: StoreWithDetails) = StoreWithDetailsEntity (
            StoreEntity.toStoreEntity(storeWithDetails.store),
            UserEntity.toUserEntity(storeWithDetails.user),
            ImageEntity.toImageEntity(storeWithDetails.image),
        )
    }
}