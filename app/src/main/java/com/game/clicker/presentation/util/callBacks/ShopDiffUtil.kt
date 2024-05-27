package com.game.clicker.presentation.util.callBacks

import androidx.recyclerview.widget.DiffUtil
import com.game.clicker.domain.models.Image

class ShopDiffUtil: DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean = oldItem.imageId == newItem.imageId

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean = oldItem == newItem
}