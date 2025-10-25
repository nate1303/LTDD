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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ScreenCreatePassWord(navController: NavHostController,sharedViewModel: SharedViewModel) {
    var password by remember { mutableStateOf(sharedViewModel.password.value) }
    var confirm by remember { mutableStateOf(sharedViewModel.password.value) }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmVisible by remember { mutableStateOf(false) }

    // 🔙 Nút quay lại
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
            .padding(vertical = 250.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        // Ô nhập mật khẩu
        OutlinedTextField(
            modifier = Modifier
                .width(350.dp)
                .height(70.dp),
            shape = RoundedCornerShape(10.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
            trailingIcon = {
                val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = "Toggle password visibility")
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(Modifier.height(8.dp))
        // Ô nhập lại mật khẩu
        OutlinedTextField(
            modifier = Modifier
                .width(350.dp)
                .height(70.dp),
            shape = RoundedCornerShape(10.dp),
            value = confirm,
            onValueChange = { confirm = it },
            label = { Text("Confirm Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Confirm Icon") },
            trailingIcon = {
                val image = if (confirmVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { confirmVisible = !confirmVisible }) {
                    Icon(imageVector = image, contentDescription = "Toggle confirm visibility")
                }
            },
            visualTransformation = if (confirmVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (password.isNotEmpty() && confirm == password) {
                    sharedViewModel.password.value = password
                    navController.navigate("ScreenConFirm")
                }
            },
            modifier = Modifier
                .width(350.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF419EFF))
        ) {
            Text(
                text = "Next",
                fontSize = 20.sp,
                color = Color.White,
            )
        }
    }

    // 🔹 Giao diện nền phía sau
    val page = Page3()
    OnBoardTemplate(
        image = painterResource(id = page.image),
        tuade = page.tuade,
        chude = page.chude,
        noidung = page.noidung
    )
}
