package com.uth.baitapvenhaflowlogingg

import android.R.attr.contentDescription
import android.R.attr.fontWeight
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.google.android.gms.auth.api.signin.GoogleSignInClient

@Composable
fun LoginScreen(googleSignInClient: GoogleSignInClient) {
    val context = androidx.compose.ui.platform.LocalContext.current as Activity

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp,vertical = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Placeholder image; add your logo at res/drawable/uth_logo.png if desired
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(200.dp),
                painter = painterResource(id = R.drawable.logo_uth),
                contentDescription = null
            )
            Text(
                "SmartTasks",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "A Simple and efficient to-do-app",
                fontSize = 18.sp,
                color = Color(0xFF2196F3)
            )
            Spacer(modifier = Modifier.height(120.dp))
            Text(
                "Welcome",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Ready to explore?Log in to get started",
                fontSize = 20.sp,
            )
        }
        Button(
            onClick = {
                // Đăng xuất tài khoản Google trước đó (để buộc chọn lại)
                googleSignInClient.signOut().addOnCompleteListener {
                    val signInIntent = googleSignInClient.signInIntent
                    context.startActivityForResult(signInIntent, MainActivity.RC_SIGN_IN)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(70.dp),
            contentPadding = PaddingValues(0.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                "SIGN IN WITH GOOGLE",
                color = Color.Black,
                fontSize = 20.sp,
            )
        }
    }
}
