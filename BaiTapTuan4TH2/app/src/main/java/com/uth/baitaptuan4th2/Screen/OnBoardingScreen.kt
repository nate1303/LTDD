package com.uth.baitaptuan4th2.Screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavHostController) {

    // --- Đây là Tính Đa Hình! ---
    // Tạo một danh sách các đối tượng từ các lớp con
    // Tất cả đều được coi là kiểu cha "OnBoardPage"
    val pages = listOf(
        PageFirst(),
        PageSecond(),
        PageThird()
    )
    // ---------------------------------

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 30.dp),
    ) {
        // Hàng 1: Dots và Nút Skip
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DotsIndicator(totalDots = pages.size, currentPage = pagerState.currentPage)

            Text(
                text = "Skip",
                fontSize = 24.sp,
                color = Color(0xFF419EFF),
                modifier = Modifier.clickable {
                    // Chuyển đến màn hình cuối cùng
                    scope.launch {
                        pagerState.animateScrollToPage(pages.lastIndex) // chuyển đến trang cuối cùng
                    }
                }
            )
        }

        // Pager để vuốt
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f) // Chiếm hết không gian ở giữa
        ) { pageIndex ->
            // Lấy dữ liệu trang hiện tại
            val page = pages[pageIndex]
            // Dùng Layout đã tạo
            OnBoardTemplate(
                image = painterResource(id = page.image),
                chude = page.chude,
                noidung = page.noidung,
//                next = { navController.navigate("") },
//                back = { navController.popBackStack() }
            )
        }
        // Hàng 3: Các nút Back/Next
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nút Back
            Button(
                onClick = {
                    if (pagerState.currentPage > 0) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    } else {
                        navController.popBackStack() // Quay lại SplashScreen
                    }
                },
                modifier = Modifier.size(60.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF419EFF)
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            // Nút Next
            Button(
                onClick = {
                    if (pagerState.currentPage < pages.lastIndex) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        // Đã ở trang cuối, đi đến màn hình chính
                        navController.navigate("SplashScreen") {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF419EFF),
                ),
            ) {
                // Thay đổi chữ trên nút tùy theo trang
                Text(
                    text = if (pagerState.currentPage == pages.lastIndex) "Get Started" else "Next",
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
        }
    }
}