package com.example.baitaptuan6_api.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable   // ✅ THÊM DÒNG NÀY
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.baitaptuan6_api.R
import com.example.baitaptuan6_api.viewmodel.Task_ViewModel

@Composable
fun List_Screen(
    vm: Task_ViewModel ,
    onTaskClick: (Int) -> Unit
) {
    LaunchedEffect(Unit) { vm.loadTasks() }
    val tasks by vm.tasks.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //logo
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(Color(0xFFE9F6FC), RoundedCornerShape(15.dp))
                    .padding(8.dp)

            ) {
                Image(
                    painter = painterResource(R.drawable.logo_uth),
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(60.dp)

                )
            }//box logo

            Column() {
                Text(
                    "SmartTasks",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
                Text("A simple and efficient to-do app", fontSize = 12.sp)
            }

            Image(
                painter = painterResource(R.drawable.icon_bell),
                contentDescription = "icon-bell",
                modifier = Modifier
                    .size(75.dp)
                    .padding(start = 44.dp)
            )
        } // Row navbar

        Spacer(Modifier.height(40.dp))
        if (tasks.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, RoundedCornerShape(20.dp))
                        .padding(50.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.task_empty),
                            contentDescription = "Task_Empty",
                            modifier = Modifier.size(160.dp)
                        )

                        Text("No Task Yet!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(
                            "Stay productive - add something to do",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(tasks) { task ->

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Red, RoundedCornerShape(20.dp))
                            .padding(15.dp)
                            .clickable { onTaskClick(task.id) },
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        Text(task.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)

                        Text(
                            task.description,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start
                        )

                        Row(
                            modifier = Modifier.padding(top = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Status: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(task.status, fontSize = 16.sp, fontWeight = FontWeight.Bold)

                            Text(
                                task.createdAt,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))
                }
            }
        }

    }
}

