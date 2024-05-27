package com.game.clicker.presentation.ui.fragment.auth.signIn

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.game.clicker.R
import com.game.clicker.databinding.FragmentAuthBinding
import com.game.clicker.presentation.ui.activities.MainActivity
import com.game.clicker.presentation.ui.base.BaseFragment
import com.game.clicker.presentation.util.addTextListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    private val viewModel: SignInViewModel by viewModels()

    override fun setListeners() {
        binding.editEmail.addTextListener(
            viewModel::isEmailCorrect,
            getString(R.string.error_email)
        )
        binding.editPassword.addTextListener(
            viewModel::isPasswordCorrect,
            getString(R.string.error_password)
        )
        addRegisterButtonListener()
        binding.signInButton.setOnClickListener {
            signInUser()
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        val intentToMain = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intentToMain)
    }

    private fun addRegisterButtonListener() {
        with(binding.buttonRegisterUser) {
            setOnClickListener {
                it.findNavController().navigate(R.id.action_authFragment_to_registerFragment)
            }
        }
    }

    private fun signInUser() {
        with(binding) {
            if (viewModel.isPasswordCorrect(editPassword.text.toString())
                && viewModel.isEmailCorrect(editEmail.text.toString())
            ) {
                viewModel.signIn(editEmail.text.toString(), editPassword.text.toString())
            }
        }
    }
}
