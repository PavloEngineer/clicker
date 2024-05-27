package com.game.clicker.presentation.ui.fragment.auth.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.clicker.data.utils.ValidationUser
import com.game.clicker.domain.models.User
import com.game.clicker.domain.use_cases.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
): ViewModel() {

    fun registerUser(user: User) = viewModelScope.launch {
        registerUserUseCase(user)
    }

    fun isEmailCorrect(email: String?): Boolean = ValidationUser.isEmailCorrect(email)

    fun isPasswordCorrect(password: String?): Boolean = ValidationUser.isPasswordCorrect(password)

    fun isPasswordsSame(password: String?, againPassword: String?): Boolean = ValidationUser.isPasswordsSame(password, againPassword)
}