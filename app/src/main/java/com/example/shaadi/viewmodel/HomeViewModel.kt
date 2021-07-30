package com.example.shaadi.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.shaadi.response.UserDetails
import com.example.shaadi.respository.UserDetailRepo
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import room.Profile
import room.ProfileDatabase
import kotlin.collections.ArrayList

class HomeViewModel constructor(val userDetailRepo: UserDetailRepo) : ViewModel(), LifecycleObserver {

    val disposable : Disposable? = null
    val itemCardDetailList :ArrayList<ItemCardDetail> = arrayListOf()
    val itemCardDetailLiveData : MutableLiveData<ArrayList<ItemCardDetail>> = MutableLiveData()
    lateinit var profileDatabase : ProfileDatabase

    fun getUserDetailsFromNetwork()
    {
        val disposable =  userDetailRepo.getUserDetailsFromRepo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onSuccess,this::onError)
    }

    fun getUserDetailsFromDatabase() {
        userDetailRepo.getUserDetailsFromDataBase(profileDatabase)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::updateValues, this::onError)
    }

    fun updateStatus(status: String, name : String)
    {
        userDetailRepo.updateStatus(profileDatabase, status, false, name)
            .subscribeOn(Schedulers.io())
            .subscribe(this::onSuccessUpdate,this::onError)
    }

    fun setDatabase(userDatabase: ProfileDatabase) {
        this.profileDatabase = userDatabase
    }

    private fun updateValues(profile: List<Profile>) {
        itemCardDetailList.clear()
        for(p in profile)
        {
            itemCardDetailList.add(ItemCardDetail(p.name, p.age, p.image,
                ObservableField(p.statusAvailabilty), ObservableField(p.status) ))
        }
        itemCardDetailLiveData.postValue(itemCardDetailList)
    }


    private fun onSuccessUpdate() {
        Log.d(">>", "Values Updated")
    }

    private fun onSuccess(userDetailResponse : UserDetails) {
       setValue(userDetailResponse)
    }

    private fun setValue(userDetailResponse: UserDetails) {
        for (i in userDetailResponse.results)
        {
            val itemCardDetail = ItemCardDetail(i.name.first.plus(" ").plus(i.name.last),
                i.dob.age.toString(), i.picture.large, ObservableField(true), ObservableField(""))
            itemCardDetailList.add(itemCardDetail)
        }
        updateDatabase(itemCardDetailList)
       itemCardDetailLiveData.postValue(itemCardDetailList)
    }

    private fun updateDatabase(i: ArrayList<ItemCardDetail>) {
        for (item in itemCardDetailList)
        Completable.fromAction { profileDatabase.profileDao().insert(
            Profile(item.name, item.age, item.image, item.isStatusVisible.get()!!, item.statusValue.get()!!)) }
            .subscribeOn(Schedulers.io())
            .subscribe(this::onSuccess, this::onError)
    }

    private fun onSuccess()
    {
        Log.d(">>", "Values Inserted")
    }

    private fun onError(throwable: Throwable) {
            Log.d(">>", "error")
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose();
    }
}