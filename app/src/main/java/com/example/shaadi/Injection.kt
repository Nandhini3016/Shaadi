package com.example.shaadi

import com.example.shaadi.respository.UserDetailRepo
import com.example.shaadi.service.UserDetailsService

object Injection {

    fun providesViewModelFactory() : ViewModelFactory {
        return ViewModelFactory(providesUserDetailsRepo())
    }

    private fun providesUserDetailsRepo(): UserDetailRepo {
            return UserDetailRepo(UserDetailsService())
    }
}