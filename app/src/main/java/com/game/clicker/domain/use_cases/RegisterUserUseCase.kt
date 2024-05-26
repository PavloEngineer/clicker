package com.game.clicker.domain.use_cases

import com.game.clicker.R
import com.game.clicker.domain.models.Image
import com.game.clicker.domain.models.Store
import com.game.clicker.domain.models.User
import com.game.clicker.domain.repositories.ImageRepository
import com.game.clicker.domain.repositories.StoreRepository
import com.game.clicker.domain.repositories.UserRepository
import com.game.clicker.domain.utils.TypeImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val storeRepository: StoreRepository,
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(user: User) {
        withContext(Dispatchers.IO) {
            userRepository.addUser(user)

            val skinImage = Image(typeImage =  TypeImage.SKIN, price = 0, url = R.drawable.skin_scratch_cat.toString())
            val backgroundImage = Image(typeImage = TypeImage.BACKGROUND, price =  0, url =  R.drawable.protruding_squares.toString())

            imageRepository.addImage(skinImage)
            imageRepository.addImage(backgroundImage)

            val currentDate = LocalDate.now()

            val skinStoreEntity = Store(userId = userRepository.getUser(user.email, user.password).userId, imageId = skinImage.imageId + 1, dateOfBuying =  currentDate)
            val backgroundStoreEntity = Store(userId = userRepository.getUser(user.email, user.password).userId, imageId = backgroundImage.imageId + 2, dateOfBuying = currentDate)

            runBlocking {
                storeRepository.insertStore(skinStoreEntity)
                storeRepository.insertStore(backgroundStoreEntity)
            }
        }
    }
}