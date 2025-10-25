package com.uth.baitapvenhatuan4

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.uth.baitapvenhatuan4.Screen.ScreenCode
import com.uth.baitapvenhatuan4.Screen.ScreenConFirm
import com.uth.baitapvenhatuan4.Screen.ScreenCreatePassWord
import com.uth.baitapvenhatuan4.Screen.ScreenForgetPassWord
import com.uth.baitapvenhatuan4.Screen.SharedViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    val sharedViewModel: SharedViewModel = viewModel()
   NavHost(navController, startDestination = "ScreenForgetPassWord"
    ) {
        composable("ScreenForgetPassWord") { ScreenForgetPassWord(navController,sharedViewModel) }
        composable("ScreenCreatePassWord") { ScreenCreatePassWord(navController,sharedViewModel) }
        composable("ScreenCode") { ScreenCode(navController,sharedViewModel) }
        composable("ScreenConFirm") { ScreenConFirm(navController,sharedViewModel) }
    }
}



