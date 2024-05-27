package com.game.clicker.data.user.repositories

import com.game.clicker.data.user.accessObjects.UserDao
import com.game.clicker.data.user.entities.UserEntity
import com.game.clicker.domain.models.User
import com.game.clicker.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): UserRepository{

    override suspend fun addUser(user: User) =
        userDao.addUser(UserEntity.toUserEntity(user))


    override suspend fun updateUser(user: User) =
        userDao.updateUser(UserEntity.toUserEntity(user))

    override suspend fun getUser(email: String, password: String): User =
         userDao.getUser(email, password).toUser()

    override suspend fun getUserById(userId: Int): User =
        userDao.getUserById(userId).toUser()
}

