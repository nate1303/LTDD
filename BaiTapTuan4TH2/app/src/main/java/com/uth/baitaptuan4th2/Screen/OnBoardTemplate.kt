package com.uth.baitaptuan4th2.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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

@Composable
fun DotsIndicator(
    totalDots: Int,
    currentPage: Int,
    activityColor: Color = Color(0xFF419EFF),
    inactiveColor: Color = Color.Gray
){
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ){
        repeat(totalDots){
            index ->
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(
                        color = if (index == currentPage) activityColor else inactiveColor,
                        shape = CircleShape
                    )
            )
        }
    }
}
@Composable
fun OnBoardTemplate(
    image: Painter,
    chude: String,
    noidung: String,
//    nextLabel: String = "Next",
//    next: () -> Unit,
//    back: (() -> Unit),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = image,
            contentDescription = chude,
            modifier = Modifier
                .fillMaxWidth()
                .size(450.dp)
        )
        Text(
            text = chude,
            fontSize = 24.sp,
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