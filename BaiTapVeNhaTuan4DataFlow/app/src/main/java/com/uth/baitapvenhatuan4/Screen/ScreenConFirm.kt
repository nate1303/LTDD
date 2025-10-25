package com.uth.baitapvenhatuan4.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ScreenConFirm(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    // Lấy dữ liệu từ ViewModel (đã lưu ở các màn hình trước)
    val Email = sharedViewModel.email.value
    val otp = sharedViewModel.otp.value      // dùng đúng tên biến
    val password = sharedViewModel.password.value
    IconButton(
        onClick = { navController.popBackStack() },
        modifier = Modifier
            .offset(y = 30.dp)
            .padding(16.dp)
            .size(50.dp)
            .background(Color(0xFF419EFF), shape = CircleShape)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.White
        )
    }
    // 🔹 Nội dung chính
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 180.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        // Email
        TextField(
            modifier = Modifier
                .width(350.dp)
                .height(60.dp),
            shape = RoundedCornerShape(10.dp),
            value = Email,
            onValueChange = {},
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "Email Icon")
            },
            textStyle = LocalTextStyle.current.copy(fontSize = 24.sp),
            enabled = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Code (OTP)
        TextField(
            modifier = Modifier
                .width(350.dp)
                .height(60.dp),
            shape = RoundedCornerShape(10.dp),
            value = otp,
            onValueChange = {},
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Code Icon")
            },
            textStyle = LocalTextStyle.current.copy(fontSize = 24.sp),
            enabled = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password
        TextField(
            modifier = Modifier
                .width(350.dp)
                .height(60.dp),
            shape = RoundedCornerShape(10.dp),
            value = password,
            onValueChange = { },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password Icon")
            },
            textStyle = LocalTextStyle.current.copy(fontSize = 24.sp),
            enabled = false
        )

        Spacer(modifier = Modifier.height(25.dp))

        // Submit button
        Button(
            onClick = {
                sharedViewModel.isFinalStep.value = true
                // Quay lại trang đầu
                navController.navigate("ScreenForgetPassWord")
            },
            modifier = Modifier
                .width(350.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF419EFF)
            )
        ) {
            Text(
                text = "Submit",
                fontSize = 20.sp,
                color = Color.White,
            )
        }
    }

    // 🔹 Template nền
    val page = Page4()
    OnBoardTemplate(
        image = painterResource(id = page.image),
        tuade = page.tuade,
        chude = page.chude,
        noidung = page.noidung
    )
}
