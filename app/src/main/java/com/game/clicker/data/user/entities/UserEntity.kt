package com.game.clicker.data.user.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.game.clicker.domain.models.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val email: String,
    val password: String,
    val fullName: String,
    val amountOfPoints: Int = 0
) {
    fun toUser(): User {
        return User(userId, email, password, fullName, amountOfPoints)
    }

    companion object {
        fun toUserEntity(user: User) = UserEntity (
            user.userId,
            user.email,
            user.password,
            user.fullName,
            user.amountOfPoints
        )
    }
}