package com.uth.baitapvenhatuan4.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ScreenCode(navController: NavHostController,sharedViewModel: SharedViewModel) {
    var otp by remember { mutableStateOf(sharedViewModel.otp.value) }
    val otpLength = 5
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .offset(y = 30.dp)
                .padding(16.dp)
                .size(50.dp)
                .background(Color(0xFF419EFF), shape = CircleShape),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 300.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            // Hàng ô nhập OTP
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 0 until otpLength) {
                    Box(
                        modifier = Modifier
                            .size(65.dp)
                            .border(
                                width = 2.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .background(
                                Color(0xFFF9F9F9),
                                shape = RoundedCornerShape(10.dp)
                            )
                            // Khi nhấn vào ô, focus vào TextField ẩn để mở bàn phím
                            .clickable { focusRequester.requestFocus() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = otp.getOrNull(i)?.toString() ?: "",
                            fontSize = 20.sp
                        )
                    }
                }
            }

            // TextField ẩn — nơi thực sự nhập ký tự
            TextField(
                value = otp,
                onValueChange = {
                    if (it.length <= otpLength && it.all { c -> c.isDigit() })
                        otp = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .size(1.dp)
                    .focusRequester(focusRequester)
                    .background(Color.Transparent),
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    if (otp.length == otpLength) {
                        sharedViewModel.otp.value = otp
                        navController.navigate("ScreenCreatePassWord")
                    }
                },
                modifier = Modifier
                    .width(350.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF419EFF)
                )
            ) {
                Text(
                    text = "Next",
                    fontSize = 20.sp,
                    color = Color.White,
                )
            }
        }
        val page = Page2()
        OnBoardTemplate(
            image = painterResource(id = page.image),
            tuade = page.tuade,
            chude = page.chude,
            noidung = page.noidung
        )
}
