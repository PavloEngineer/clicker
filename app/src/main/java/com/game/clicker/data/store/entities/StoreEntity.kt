package com.game.clicker.data.store.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.game.clicker.data.image.entities.ImageEntity
import com.game.clicker.data.user.entities.UserEntity
import com.game.clicker.domain.models.Store
import java.time.LocalDate

@Entity(
    tableName = "store",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ImageEntity::class,
            parentColumns = ["imageId"],
            childColumns = ["imageId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["userId"]),
        Index(value = ["imageId"])
    ]
)
data class StoreEntity(
    @PrimaryKey(autoGenerate = true)
    val storeId: Int,
    val userId: Int,
    val imageId: Int,
    val dateOfBuying: LocalDate
) {
    fun toStore(): Store {
        return Store(storeId, userId, imageId, dateOfBuying)
    }

    companion object {
        fun toStoreEntity(store: Store) = StoreEntity (
            store.storeId,
            store.userId,
            store.imageId,
            store.dateOfBuying
        )
    }
}