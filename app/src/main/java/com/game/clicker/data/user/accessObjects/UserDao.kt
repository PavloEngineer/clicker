package com.game.clicker.data.user.accessObjects

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.game.clicker.data.user.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(userEntity: UserEntity)

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun getUser(email: String, password: String): UserEntity

    @Query("SELECT * FROM user WHERE userId = :userId")
    fun getUserById(userId: Int): UserEntity
}