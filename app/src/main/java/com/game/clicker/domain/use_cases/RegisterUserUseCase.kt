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
import kotlinx.coroutines.flow.first
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
            var isImagesListEmpty: Boolean

            runBlocking {
                userRepository.addUser(user)
                isImagesListEmpty = imageRepository.getAllImages().first().isEmpty()
            }

            if (isImagesListEmpty) {
                fillImages()
            }

            fillStore(user)
        }
    }

    private suspend fun fillImages() {
        val skinImage = Image(
            typeImage = TypeImage.SKIN,
            price = 0,
            url = R.drawable.skin_scratch_cat.toString()
        )
        val backgroundImage = Image(
            typeImage = TypeImage.BACKGROUND,
            price = 0,
            url = R.drawable.protruding_squares.toString()
        )

        val backgroundImageGreen = Image(
            typeImage = TypeImage.BACKGROUND,
            price = 100,
            url = R.drawable.backround_tortoise_shell.toString()
        )

        runBlocking {
            imageRepository.addImage(skinImage)
            imageRepository.addImage(backgroundImage)
            imageRepository.addImage(backgroundImageGreen)
        }
    }

    private suspend fun fillStore(user: User) {
        val currentDate = LocalDate.now()
        val skinStoreEntity = Store(
            userId = userRepository.getUser(user.email, user.password).userId,
            imageId = 1,
            dateOfBuying = currentDate
        )
        val backgroundStoreEntity = Store(
            userId = userRepository.getUser(user.email, user.password).userId,
            imageId = 2,
            dateOfBuying = currentDate
        )

        runBlocking {
            storeRepository.insertStore(skinStoreEntity)
            storeRepository.insertStore(backgroundStoreEntity)
        }
    }
}