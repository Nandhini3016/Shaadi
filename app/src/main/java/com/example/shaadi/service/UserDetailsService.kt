package com.example.shaadi.service

import com.example.shaadi.response.UserDetails
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UserDetailsInterface
{
    @GET("?results=10")
    fun getUserDetails() : Single<UserDetails>
}

class UserDetailsService {
        fun buildRetrofit() = Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val service : UserDetailsInterface = buildRetrofit().create(UserDetailsInterface::class.java
    )
}