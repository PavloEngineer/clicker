package com.game.clicker.domain.repositories

import com.game.clicker.domain.models.User

interface UserRepository {

    suspend fun addUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun getUser(email: String, password: String): User

    suspend fun getUserById(userId: Int): User
}