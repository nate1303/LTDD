package com.uth.baitapvenhatuan4.Screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uth.baitapvenhatuan4.R

@Composable
fun OnBoardTemplate(
    image: Painter,
    tuade: String,
    chude: String,
    noidung: String,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp,vertical = 150.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Image(
            painter = image,
            contentDescription = tuade,
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = tuade,
            fontSize = 34.sp,
            color = Color(0xFF419EFF),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = chude,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = noidung,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
        )
    }
}

open class OnBoardPage(
    @DrawableRes val image: Int,
    val tuade: String,
    val chude: String,
    val noidung: String,
)

class Page1 : OnBoardPage(
    image = R.drawable.logo_uth,
    tuade = "SmartTasks",
    chude = "Forget PassWord?",
    noidung = "Enter your Email, we will send you a verification code"
)

class Page2 : OnBoardPage(
    image = R.drawable.logo_uth,
    tuade = "SmartTasks",
    chude = "Verify Code",
    noidung = "Enter the code, we just send you on your registered Email"
)

class Page3 : OnBoardPage(
    image = R.drawable.logo_uth,
    tuade = "SmartTasks",
    chude = "Create New PassWord",
    noidung = "Your new password must be different from previous used passwords"
)

class Page4 : OnBoardPage(
    image = R.drawable.logo_uth,
    tuade = "SmartTasks",
    chude = "Confirm",
    noidung = "We are here to help you!"
)