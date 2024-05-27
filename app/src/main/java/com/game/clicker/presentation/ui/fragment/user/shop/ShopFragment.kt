package com.game.clicker.presentation.ui.fragment.user.shop

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.game.clicker.R
import com.game.clicker.databinding.FragmentShopBinding
import com.game.clicker.domain.models.Image
import com.game.clicker.presentation.ui.base.BaseFragment
import com.game.clicker.presentation.ui.fragment.user.shop.adapter.ShopAdapter
import com.game.clicker.presentation.ui.interfaces.ShopAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ShopFragment: BaseFragment<FragmentShopBinding>(FragmentShopBinding::inflate)  {

    private val viewModel: ShopViewModel by viewModels()

    private val shopAdapter by lazy {
        ShopAdapter(
            listener = object : ShopAdapterListener {
                override fun onItemClickBuy(image: Image) {
                    viewModel.buyImage(image)
                }
            }
        )
    }

    override fun onStart() {
        super.onStart()
        setObserverImage()
        setObserverUser()
    }

    private fun setObserverImage() {
        viewModel.images.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                shopAdapter.submitList(data)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setObserverUser() {
        viewModel.user.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                binding.textScoreTotalShop.text = "${data.amountOfPoints}"
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun setListeners() {
        super.setListeners()
        binding.buttonBack.setOnClickListener{findNavController().navigateUp()}
    }

    private fun setupRecyclerView() {
        binding.recyclerImages.adapter = shopAdapter
        binding.recyclerImages.layoutManager = LinearLayoutManager(requireContext())
    }
}