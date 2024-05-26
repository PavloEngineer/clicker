package com.game.clicker.presentation.ui.fragment.user.play_mode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.clicker.domain.models.AppSettings
import com.game.clicker.domain.models.User
import com.game.clicker.domain.use_cases.GetUserByIdUseCase
import com.game.clicker.domain.use_cases.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PlayModeViewModel @Inject constructor(
    private val appSettings: AppSettings,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase
): ViewModel() {

    private var _score: MutableStateFlow<Int> = MutableStateFlow(0)
    val score: StateFlow<Int> = _score

    private var _user: MutableStateFlow<User> = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    init{
        getUser()
    }

    fun getUser() {
            viewModelScope.launch {
                val userId = appSettings.getUserId()
                userId?.run {
                    _user.value = getUserByIdUseCase(userId)
                }
            }
    }

    fun plusOneToScore() = _score.value++

    fun incrementResultToMainScore() {
        viewModelScope.launch {
            user.value.amountOfPoints += _score.value
            updateUserUseCase(user.value)
        }
    }
}