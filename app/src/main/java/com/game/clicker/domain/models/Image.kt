package com.game.clicker.domain.models

import com.game.clicker.domain.utils.TypeImage

data class Image(
    val imageId: Int,
    val typeImage: TypeImage,
    val url: String
)