package com.game.clicker.presentation.ui.fragment.auth.signUp


import androidx.fragment.app.viewModels
import com.game.clicker.databinding.FragmentRegisterBinding
import com.game.clicker.domain.models.User
import com.game.clicker.presentation.ui.base.BaseFragment
import com.game.clicker.presentation.util.addTextListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.game.clicker.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment: BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: SignUpViewModel by viewModels()

    override fun setListeners() {
        super.setListeners()
        addCancelButtonListener()
        addRegisterButtonListener()
        binding.editEmail.addTextListener(viewModel::isEmailCorrect, getString(R.string.error_email))
        binding.editPassword.addTextListener( viewModel::isPasswordCorrect, getString(R.string.error_password))
    }

    private fun addCancelButtonListener() {
        binding.buttonCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun addRegisterButtonListener() {
        binding.buttonRegister.setOnClickListener {
            if (isAllFieldCorrect()) {

                with(binding) {
                    val user: User = User(
                        fullName = editFullName.text.toString(),
                        password = editPassword.text.toString(),
                        email = editEmail.text.toString()
                    )

                    viewModel.registerUser(user)
                    findNavController().navigateUp()
                }
            }
        }
    }

    private fun isAllFieldCorrect(): Boolean {
        with(binding) {
            return viewModel.isEmailCorrect(editEmail.text.toString()) &&
                    viewModel.isPasswordCorrect(editPassword.text.toString()) &&
                    (editFullName.text?.isNotEmpty() ?: "") as Boolean
        }
    }
}