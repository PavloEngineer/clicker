package com.game.clicker.presentation.ui.fragment.user.play_mode.dialog_result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.game.clicker.databinding.DialogResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultDialog : DialogFragment() {

    private var _binding: DialogResultBinding? = null
    private val binding get() = _binding!!

    private val args: ResultDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textScorePerGame.text = args.scoreDuringGame.toString()
        setListeners()
    }

    private fun setListeners() {
        binding.buttonMenu.setOnClickListener {
            findNavController().navigate(ResultDialogDirections.actionResultDialogToMenuFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}