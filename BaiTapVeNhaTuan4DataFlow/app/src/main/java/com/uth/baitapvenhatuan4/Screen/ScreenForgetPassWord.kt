package com.uth.baitapvenhatuan4.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ScreenForgetPassWord(navController: NavHostController,sharedViewModel: SharedViewModel){
    var email by remember { mutableStateOf(sharedViewModel.email.value) }
    var Email = sharedViewModel.email.value
    var otp = sharedViewModel.otp.value
    var password = sharedViewModel.password.value
    val isFinalStep = sharedViewModel.isFinalStep.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 320.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        OutlinedTextField(
            modifier = Modifier
                .width(350.dp)
                .height(70.dp),
            shape = RoundedCornerShape(10.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text("Your Email") },
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "Email Icon"
                )
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                sharedViewModel.email.value = email
                navController.navigate("ScreenCode")
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
    if(isFinalStep) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 70.dp),
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
        }
    }
    Spacer(modifier = Modifier.height(25.dp))
    val page = Page1()
    OnBoardTemplate(
        image = painterResource(id = page.image),
        tuade = page.tuade,
        chude = page.chude,
        noidung = page.noidung
    )
}
