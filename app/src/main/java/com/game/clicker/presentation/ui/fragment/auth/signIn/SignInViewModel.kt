package com.game.clicker.presentation.ui.fragment.auth.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.clicker.data.utils.ValidationUser
import com.game.clicker.domain.models.AppSettings
import com.game.clicker.domain.use_cases.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val appSettings: AppSettings
): ViewModel() {

    fun signIn(email: String, password: String) = viewModelScope.launch() {
        val userId = signInUseCase(email, password).userId
        appSettings.setUserId(userId)
    }

    fun isEmailCorrect(email: String?): Boolean = ValidationUser.isEmailCorrect(email)

    fun isPasswordCorrect(password: String?): Boolean = ValidationUser.isPasswordCorrect(password)
}