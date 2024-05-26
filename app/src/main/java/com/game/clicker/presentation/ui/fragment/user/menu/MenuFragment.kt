package com.game.clicker.presentation.ui.fragment.user.menu

import android.R
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.game.clicker.databinding.FragmentMenuBinding
import com.game.clicker.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MenuFragment: BaseFragment<FragmentMenuBinding>(FragmentMenuBinding::inflate)  {

    private val viewModel: MenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSkinsByUser()
        viewModel.getBackgroundsByUser()
    }

    override fun onStart() {
        super.onStart()
        viewModel.storeOfSkins.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                setupSkinsSpinner()
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.storeOfBackgrounds.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                setupStoreOfBackgroundsSpinner()
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setupSkinsSpinner() {
        val skins: List<String> = viewModel.storeOfSkins.value.map { "${it.image.imageId}" }
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, skins)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.editTypeSkin.adapter = adapter
    }

    private fun setupStoreOfBackgroundsSpinner() {
        val background: List<String> = viewModel.storeOfBackgrounds.value.map { "${it.image.imageId}" }
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, background)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.editChooseBackground.adapter = adapter
    }

    override fun setListeners() {
        super.setListeners()
        setupExitButtonListener()
        setupPlayButtonListener()
        setupShopButtonListener()
    }

    private fun setupExitButtonListener() {
        binding.buttonExit.setOnClickListener {
            activity?.finish()
        }
    }

    private fun setupPlayButtonListener() {
        binding.buttonPlay.setOnClickListener {
            val skinUrl = getSkinUrl()
            val backgroundUrl = getBackgroundUrl()
            val direction = MenuFragmentDirections.actionMenuFragmentToPlayModeFragment(skinUrl.toString(), backgroundUrl.toString())
            findNavController().navigate(direction)
        }
    }

    private fun setupShopButtonListener() {
        binding.buttonShop.setOnClickListener {
            // Add your shop button logic here
        }
    }

    private fun getSkinUrl(): Int {
        try {
            val selectedSkinId: String = binding.editTypeSkin.selectedItem as String
            val skin = viewModel.storeOfSkins.value.find { it.image.imageId == selectedSkinId.toInt() }
            return skin?.image?.url?.toInt() ?: 0
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }

    private fun getBackgroundUrl(): Int {
        try {
            val selectedBackgroundId = binding.editChooseBackground.selectedItem as String
            val background = viewModel.storeOfBackgrounds.value.find { it.image.imageId == selectedBackgroundId.toInt() }
            return background?.image?.url?.toInt() ?: 0
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return  0
    }
}