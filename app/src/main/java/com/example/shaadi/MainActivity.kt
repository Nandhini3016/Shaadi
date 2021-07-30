package com.example.shaadi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shaadi.adaptor.UserDetailAdaptor
import com.example.shaadi.databinding.ActivityMainBinding
import com.example.shaadi.viewmodel.HomeViewModel
import com.example.shaadi.viewmodel.ItemCardDetail
import room.ProfileDatabase
import android.net.NetworkInfo

import android.net.Network

import android.os.Build

import android.net.NetworkCapabilities

import android.net.ConnectivityManager




class MainActivity : AppCompatActivity(),LifecycleObserver {

  lateinit var homeViewModel : HomeViewModel
   var profileDatabase : ProfileDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        homeViewModel = ViewModelProvider(this,
            Injection.providesViewModelFactory()).get(HomeViewModel::class.java)
        binding.viewModel = homeViewModel

        lifecycle.addObserver(homeViewModel)


        profileDatabase = ProfileDatabase.getDatabaseInstance(this)
        profileDatabase?.let { homeViewModel.setDatabase(it) }

        if(isNetworkAvailable())
            homeViewModel.getUserDetailsFromNetwork()
        else
            homeViewModel.getUserDetailsFromDatabase()


        homeViewModel.itemCardDetailLiveData.observe(this,
            Observer<ArrayList<ItemCardDetail>> {
        itemCardViewModel ->
        val adapter = UserDetailAdaptor(itemCardViewModel, this@MainActivity)
        binding.idRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.idRecyclerview.adapter = adapter
    })
}

    fun isNetworkAvailable(): Boolean {
        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            ?: return false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val cap = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
            return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val networks = cm.allNetworks
            for (n in networks) {
                val nInfo = cm.getNetworkInfo(n)
                if (nInfo != null && nInfo.isConnected) return true
            }
        } else {
            val networks = cm.allNetworkInfo
            for (nInfo in networks) {
                if (nInfo != null && nInfo.isConnected) return true
            }
        }
        return false
    }
}