package com.uth.baitaptuan4th2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.uth.baitaptuan4th2.ui.theme.BaiTapTuan4TH2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapTuan4TH2Theme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}