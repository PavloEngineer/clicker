package com.game.clicker.presentation.ui.fragment.user.shop.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.game.clicker.R
import com.game.clicker.databinding.ItemImageBinding
import com.game.clicker.domain.models.Image
import com.game.clicker.presentation.ui.interfaces.ShopAdapterListener
import com.game.clicker.presentation.util.callBacks.ShopDiffUtil

class ShopAdapter(
    val listener: ShopAdapterListener
): ListAdapter<Image, ShopAdapter.ShopViewHolder>(ShopDiffUtil()) {

    inner class ShopViewHolder(
        private val binding: ItemImageBinding
    ): RecyclerView.ViewHolder(binding.root) {


        fun bind(image: Image) {
            with(binding) {
                inputPrice.text = "${image.price}"
                imageItem.setImageResource(image.url.toInt())
                setImage(image)
                buttonBuy.setOnClickListener{
                    listener.onItemClickBuy(image)
                }
            }
        }

        private fun setImage(image: Image){
            try {
                val resourceId = image.url.toInt()
                binding.imageItem.setImageResource(resourceId)
            } catch (e: NumberFormatException) {
                binding.imageItem.setImageResource(R.drawable.skin_scratch_cat)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShopViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(
            inflate,
            parent,
            false
        )
        return ShopViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ShopViewHolder,
        position: Int
    ) = holder.bind(getItem(position))
}