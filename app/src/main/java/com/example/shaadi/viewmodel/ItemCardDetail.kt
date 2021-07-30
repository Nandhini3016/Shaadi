package com.example.shaadi.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import java.util.*

data class ItemCardDetail(val name : String,
                          val age : String,
                          val image : String,
                          var isStatusVisible : ObservableField<Boolean>,
                          var statusValue : ObservableField<String>)
