package com.game.clicker.domain.models

import java.time.LocalDate

data class Store (
    val storeId: Int,
    val userId: Int,
    val imageId: Int,
    val dateOfBuying: LocalDate
)