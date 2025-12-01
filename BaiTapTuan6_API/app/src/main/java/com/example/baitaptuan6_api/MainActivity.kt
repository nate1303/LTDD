package com.example.baitaptuan6_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.baitaptuan6_api.navigation.AppNavigation
import com.example.baitaptuan6_api.ui.theme.BaiTapTuan6_APITheme
import com.example.baitaptuan6_api.viewmodel.Task_ViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapTuan6_APITheme {
                Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        val vm: Task_ViewModel = viewModel()
                        AppNavigation(vm)
                    }
                }
            }
        }
    }
}

