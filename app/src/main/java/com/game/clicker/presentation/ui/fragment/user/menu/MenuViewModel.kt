package com.game.clicker.presentation.ui.fragment.user.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.clicker.domain.models.AppSettings
import com.game.clicker.domain.models.StoreWithDetails
import com.game.clicker.domain.use_cases.GetAllBackgroundForUserUseCase
import com.game.clicker.domain.use_cases.GetAllSkinsUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val appSettings: AppSettings,
    private val getAllSkinsUserIdUseCase: GetAllSkinsUserIdUseCase,
    private val getAllBackgroundForUserUseCase: GetAllBackgroundForUserUseCase
): ViewModel() {

    private val _storeOfSkins = MutableStateFlow<List<StoreWithDetails>>(emptyList())
    val storeOfSkins: StateFlow<List<StoreWithDetails>> = _storeOfSkins

    private val _storeOfBackgrounds = MutableStateFlow<List<StoreWithDetails>>(emptyList())
    val storeOfBackgrounds: StateFlow<List<StoreWithDetails>> = _storeOfBackgrounds

    fun getSkinsByUser() {
        viewModelScope.launch {
            val userId = appSettings.getUserId()
            userId?.run {
                _storeOfSkins.value = getAllSkinsUserIdUseCase(userId).first()
            }
        }
    }

    fun getBackgroundsByUser() {
        viewModelScope.launch {
            val userId = appSettings.getUserId()
            userId?.run {
                _storeOfBackgrounds.value = getAllBackgroundForUserUseCase(userId).first()
            }
        }
    }
}