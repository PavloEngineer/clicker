package com.game.clicker.presentation.ui.fragment.user.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.clicker.domain.models.AppSettings
import com.game.clicker.domain.models.Image
import com.game.clicker.domain.models.Store
import com.game.clicker.domain.models.User
import com.game.clicker.domain.use_cases.GetNotBoughtImagesUseCase
import com.game.clicker.domain.use_cases.GetUserByIdUseCase
import com.game.clicker.domain.use_cases.InsertStoreUseCase
import com.game.clicker.domain.use_cases.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val getNotBoughtImagesUseCase: GetNotBoughtImagesUseCase,
    private val insertStoreUseCase: InsertStoreUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val appSettings: AppSettings
): ViewModel()  {

    private var _images: MutableStateFlow<List<Image>> = MutableStateFlow(emptyList())
    val images: StateFlow<List<Image>> = _images

    private var _user: MutableStateFlow<User> = MutableStateFlow(User())
    val user: StateFlow<User> = _user


    init {
        getImages()
        getUser()
    }

    private fun getImages() {
        viewModelScope.launch {
            val userId = appSettings.getUserId()
            userId?.run {
                _images.value = getNotBoughtImagesUseCase(userId).first()
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            val userId = appSettings.getUserId()
            userId?.run {
                _user.value = getUserByIdUseCase(userId)
            }
        }
    }

    fun buyImage(image: Image) {
        viewModelScope.launch {
            if (image.price > _user.value.amountOfPoints) return@launch

            val userId = appSettings.getUserId()
            userId?.run {
                val currentDate = LocalDate.now()
                val newItemStore = Store(
                    userId = userId,
                    imageId = image.imageId,
                    dateOfBuying = currentDate
                )

                insertStoreUseCase(newItemStore)
                _user.value = _user.value.copy(amountOfPoints = _user.value.amountOfPoints - image.price)
                updateUserUseCase(_user.value)
                _images.value = _images.value.filterNot { it.imageId == image.imageId }
            }
        }
    }
}