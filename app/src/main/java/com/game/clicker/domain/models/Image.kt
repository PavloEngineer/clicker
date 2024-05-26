package com.game.clicker.domain.models

import com.game.clicker.domain.utils.TypeImage

data class Image(
    val imageId: Int = 0,
    val typeImage: TypeImage,
    val price: Int,
    val url: String
)