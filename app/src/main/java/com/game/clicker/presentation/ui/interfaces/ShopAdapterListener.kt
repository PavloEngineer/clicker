package com.game.clicker.presentation.ui.interfaces

import com.game.clicker.domain.models.Image

interface ShopAdapterListener {

    fun onItemClickBuy(image: Image)
}