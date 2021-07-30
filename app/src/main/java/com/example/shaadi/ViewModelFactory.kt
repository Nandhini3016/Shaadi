package com.example.shaadi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shaadi.respository.UserDetailRepo
import com.example.shaadi.viewmodel.HomeViewModel

class ViewModelFactory constructor(val userDetailRepo: UserDetailRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            return HomeViewModel(userDetailRepo) as T
        else
            throw IllegalAccessException("UnKnown Class")
    }
}