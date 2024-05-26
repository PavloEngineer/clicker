package com.game.clicker.presentation.ui.fragment.user.play_mode


import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.game.clicker.R
import com.game.clicker.databinding.FragmentPlayModeBinding
import com.game.clicker.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PlayModeFragment: BaseFragment<FragmentPlayModeBinding>(FragmentPlayModeBinding::inflate)  {

    companion object {
        const val DURATION_ANIMATION: Long = 200
        const val VIEW_ANIMATION_SCALE = 1.2f
        const val VIEW_STANDARD_SCALE = 1f
    }

    private val viewModel: PlayModeViewModel by viewModels()

    private val args: PlayModeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.backgroundUrl.isNotEmpty() && args.skinUrl.isNotEmpty()) {
            binding.root.setBackgroundResource(args.backgroundUrl.toInt())
            binding.imageSkin.setImageResource(args.skinUrl.toInt())
        }
    }

    override fun onStart() {
        super.onStart()
        setObserverScore()
        setObserverUser()
    }

    private fun setObserverScore() {
        viewModel.score.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                binding.textPresentScore.text = data.toString()
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    @SuppressLint("SetTextI18n")
    private fun setObserverUser() {
        viewModel.user.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                binding.labelName.text = data.fullName
                binding.textScore.text = "${getText(R.string.label_total_score)} ${data.amountOfPoints}"
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun setListeners() {
        super.setListeners()
        setupSkinJumpAnimation()
        binding.buttonStop.setOnClickListener{
            viewModel.incrementResultToMainScore()
            val direction = PlayModeFragmentDirections.actionPlayModeFragmentToResultDialog(viewModel.score.value)
            findNavController().navigate(direction)
        }
    }

    private fun setupSkinJumpAnimation() {
        binding.imageSkin.setOnClickListener {
            viewModel.plusOneToScore()
            it.animate()
                .scaleX(VIEW_ANIMATION_SCALE)
                .scaleY(VIEW_ANIMATION_SCALE)
                .setDuration(DURATION_ANIMATION)
                .withEndAction {
                    it.animate()
                        .scaleX(VIEW_STANDARD_SCALE)
                        .scaleY(VIEW_STANDARD_SCALE)
                        .setDuration(DURATION_ANIMATION)
                        .start()
                }
                .start()
        }
    }
}