package com.uth.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlignmentDemoScreen()
        }
    }
}

@Composable
fun AlignmentDemoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E))
            .padding(20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(30.dp, alignment = Alignment.CenterVertically)
    ) {
        ColumnDemo()
        RowDemo()
        BoxDemo()
    }
}
@Composable
fun ColumnDemo() {
    Text(
        text = "I. Column Alignment",
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2E2E2E), RoundedCornerShape(12.dp))
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {
            BoxItem()
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BoxItem()
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End,
        ) {
            BoxItem()
        }
    }
}

@Composable
fun RowDemo() {
    Text(
        text = "II. Row Alignment",
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color(0xFF2E2E2E), RoundedCornerShape(12.dp)),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) { BoxItem() }

            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) { BoxItem() }

            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) { BoxItem() }
        }
    }

@Composable
fun BoxDemo() {
    Text(
        text = "III. Box Alignment",
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    Box(
        modifier = Modifier
            .size(250.dp)
            .background(Color(0xFF2E2E2E), RoundedCornerShape(12.dp))
    ) {
        Icon(
            imageVector = Icons.Default.AirplanemodeActive,
            contentDescription = null,
            tint = Color.Yellow,
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(32.dp)
        )
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = Color.Cyan,
            modifier = Modifier
                .align(Alignment.Center)
                .size(32.dp)
        )
        Icon(
            imageVector = Icons.Default.ThumbUp,
            contentDescription = null,
            tint = Color.Green,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(32.dp)
        )
    }
}
@Composable
fun BoxItem() {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(Color(0xFF00C853))
    )
}
