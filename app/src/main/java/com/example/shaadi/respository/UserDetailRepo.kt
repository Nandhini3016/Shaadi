package com.example.shaadi.respository

import com.example.shaadi.response.UserDetails
import com.example.shaadi.service.UserDetailsService
import io.reactivex.Completable
import io.reactivex.Single
import room.Profile
import room.ProfileDatabase

class UserDetailRepo constructor(val userDetailsService: UserDetailsService) {

    fun getUserDetailsFromRepo(): Single<UserDetails> {
        return userDetailsService.service.getUserDetails()
    }

    fun getUserDetailsFromDataBase(profileDatabase: ProfileDatabase): Single<List<Profile>>
    {
        return Single.fromCallable {  profileDatabase.profileDao().getProfiles()}
    }

    fun updateStatus(profileDatabase: ProfileDatabase, status : String, statusVisible : Boolean, name: String) : Completable {
        return Completable.fromCallable{ profileDatabase.profileDao().updateStatus(statusVisible, status, name) }
    }

}