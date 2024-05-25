package com.game.clicker.domain.models

data class User(
    val userId: Int = 0,
    val email: String,
    val password: String,
    val fullName: String,
    val amountOfPoints: Int = 0
)