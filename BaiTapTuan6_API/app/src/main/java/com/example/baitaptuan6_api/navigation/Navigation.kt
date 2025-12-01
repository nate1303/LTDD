package com.example.baitaptuan6_api.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.baitaptuan6_api.ui.screen.Detail_Screen
import com.example.baitaptuan6_api.ui.screen.List_Screen
import com.example.baitaptuan6_api.viewmodel.Task_ViewModel

@Composable
fun AppNavigation(vm: Task_ViewModel ) {
    val navController = rememberNavController()
    // Shared ViewModel instance for all screens in this NavHost


    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            List_Screen(vm = vm, onTaskClick = { id ->
                navController.navigate("detail/$id")
            })
        }

        composable(
            route = "detail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("taskId") ?: 0
            // pass shared VM to detail, plus onBack
            Detail_Screen(
                vm = vm,
                taskId = id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
