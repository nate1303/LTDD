package com.uth.baitapvenhatuan4.Screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
class SharedViewModel: ViewModel(){
    var email = mutableStateOf("")
    var otp = mutableStateOf("")
    var password = mutableStateOf("")
    var isFinalStep = mutableStateOf(false)
}