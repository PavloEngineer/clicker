package com.game.clicker.data.image.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.game.clicker.domain.models.Image
import com.game.clicker.domain.utils.TypeImage

@Entity(tableName = "image")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val imageId: Int,
    val typeImage: TypeImage,
    val price: Int,
    val url: String
) {

    fun toImage(): Image {
        return Image(imageId, typeImage, price , url)
    }

    companion object {
        fun toImageEntity(image: Image) = ImageEntity (
            image.imageId,
            image.typeImage,
            image.price,
            image.url,
        )
    }
}