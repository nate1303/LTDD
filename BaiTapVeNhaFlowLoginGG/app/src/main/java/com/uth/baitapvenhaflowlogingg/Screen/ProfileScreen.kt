package com.uth.baitapvenhaflowlogingg

import android.R.attr.fontWeight
import android.R.attr.text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(
    googleSignInClient: GoogleSignInClient,
    navController: NavController,
    name: String?,
    email: String?,
    photoUrl: String?
) {
    var dateOfBirth by remember { mutableStateOf("11/08/2005") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
            // Nút Back
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 0.dp, y = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = {
                    googleSignInClient.signOut().addOnCompleteListener {
                        FirebaseAuth.getInstance().signOut()
                        navController.navigate("login") { popUpTo("home") { inclusive = true } } } }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF00AEEF),
                                modifier = Modifier.size(40.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 0.dp, y = -20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Profile",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00AEEF)
                )
            }

        Spacer(modifier = Modifier.height(20.dp))

        // Ảnh đại diện
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.BottomEnd
        ) {
            if (photoUrl != null) {
                Image(
                    painter = rememberAsyncImagePainter(photoUrl),
                    contentDescription = "User Photo",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Change photo",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
                    .background(Color(0xFF00AEEF), CircleShape)
                    .clip(CircleShape)
                    .padding(4.dp)
            )
        }
        Column () {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Name",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            // Trường Name
            OutlinedTextField(
                value = name ?: "",
                onValueChange = {},
                label = { Text("") },
                readOnly = true,
                shape = RoundedCornerShape(10.dp),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 18.sp,
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Email",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            // Trường Email
            OutlinedTextField(
                value = email ?: "",
                onValueChange = {},
                label = { Text("") },
                readOnly = true,
                shape = RoundedCornerShape(10.dp),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 18.sp,
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Date of Birth",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Trường Date of Birth
            OutlinedTextField(
                value = dateOfBirth,
                onValueChange = { dateOfBirth = it },
                label = { Text("") },
                readOnly = true,
                shape = RoundedCornerShape(10.dp),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 18.sp,
                ),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.rotate(270f) // biểu tượng mũi tên xuống
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(32.dp))

        // Nút Back (giống ảnh)
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00AEEF)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Back", color = Color.White, fontSize = 18.sp)
        }
    }
}
