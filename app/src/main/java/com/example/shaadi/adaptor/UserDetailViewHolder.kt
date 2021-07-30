package com.example.shaadi.adaptor

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shaadi.databinding.ItemCardDetailBinding
import com.example.shaadi.viewmodel.ItemCardDetail

class UserDetailViewHolder(val binding : ItemCardDetailBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemCardViewModel: ItemCardDetail) {
         binding.itemViewModel = itemCardViewModel
         binding.executePendingBindings()
    }
}