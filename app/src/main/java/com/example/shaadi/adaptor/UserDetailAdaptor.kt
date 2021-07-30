package com.example.shaadi.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shaadi.MainActivity
import com.example.shaadi.databinding.ItemCardDetailBinding
import com.example.shaadi.viewmodel.HomeViewModel
import com.example.shaadi.viewmodel.ItemCardDetail
import com.squareup.picasso.Picasso

class UserDetailAdaptor(
    val itemCardViewModelList: ArrayList<ItemCardDetail>,
    val mainActivity: MainActivity
) : RecyclerView.Adapter<UserDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardDetailBinding.inflate(inflater, parent, false)
        return UserDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserDetailViewHolder, position: Int) {
        val itemCardViewModel = itemCardViewModelList[position]
        if(itemCardViewModel.image!= null)
            Picasso.get().load(itemCardViewModel.image).into(holder.binding.imageHolder)
        holder.bind(itemCardViewModel)
        val viewModel = ViewModelProvider(mainActivity).get(HomeViewModel::class.java)
        holder.binding.idButtonAccept.setOnClickListener {
            itemCardViewModel.isStatusVisible.set(false)
            itemCardViewModel.statusValue.set("Accepted")
            viewModel.updateStatus("Accepted", itemCardViewModel.name)
        }

        holder.binding.idButtonDecline.setOnClickListener {
            itemCardViewModel.isStatusVisible.set(false)
            itemCardViewModel.statusValue.set("Declined")
            viewModel.updateStatus("Declined", itemCardViewModel.name)
        }
    }

    override fun getItemCount(): Int {
        return itemCardViewModelList.size
    }

}