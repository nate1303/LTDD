package com.uth.baitaptuan4th2

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.uth.baitaptuan4th2.Screen.OnBoardingScreen
import com.uth.baitaptuan4th2.Screen.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "SplashScreen") {
        composable("SplashScreen") { SplashScreen(navController) }
//        composable("ScreenFirst") { ScreenFirst(navController) }
//        composable("ScreenSecond") { ScreenSecond(navController) }
//        composable("ScreenThird") { ScreenThird(navController) }
        composable("OnBoardingScreen") {
            OnBoardingScreen(navController = navController)
        }
    }
}
