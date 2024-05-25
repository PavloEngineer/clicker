package com.game.clicker.domain.use_cases


import com.game.clicker.domain.models.User
import com.game.clicker.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): User {
        return withContext(Dispatchers.IO) {
            userRepository.getUser(email, password)
        }
    }
}