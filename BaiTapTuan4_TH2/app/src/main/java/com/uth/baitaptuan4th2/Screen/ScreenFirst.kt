package com.uth.baitaptuan4th2.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ScreenFirst(navController: NavHostController) {
    Column (
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ){
                DotsIndicator(totalDots = 3, currentPage = 0)
            }
                Text(
                    text = "Skip",
                    fontSize = 24.sp,
                    color = Color(0xFF419EFF),
                    modifier = Modifier.clickable {
                        navController.navigate("ScreenSecond")
                    }
                )
        }
        val page = PageFirst()
        OnBoardTemplate(
            image = painterResource(id = page.image),
            chude = page.chude,
            noidung = page.noidung,
//            next = { navController.navigate("ScreenSecond") },
//            back = { navController.popBackStack() }
        )
    }
}
