package com.uth.baitapthuviensach.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uth.baitapthuviensach.Model.Book
import com.uth.baitapthuviensach.Model.Student

@Composable
fun ManagementScreen(
    student: Student,
    onChangeStudent: () -> Unit,
    onAddBook: () -> Unit,
    hasStudents: Boolean,
    onReturnBook: (Book) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ---- Title ----
        Text(
            text = "Hệ thống",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Quản lý Thư viện",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        if (hasStudents) {
            // PHẦN TRÊN: Thông tin sinh viên + danh sách sách
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Sinh viên",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = student.name,
                        onValueChange = {},
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        enabled = false
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = onChangeStudent,
                        modifier = Modifier.height(55.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1))
                    ) {
                        Text("Thay đổi")
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Danh sách sách",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color(0xFFE3E3E3), RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    items(student.borrowedBooks) { book ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            shape = RoundedCornerShape(25.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Checkbox(
                                    checked = true,
                                    onCheckedChange = { onReturnBook(book) },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = Color(0xFFA00037)
                                    )
                                )
                                Text(book.title, fontSize = 16.sp)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            // ---- Nút Thêm cố định ----
            Button(
                onClick = onAddBook,
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1))
            ) {
                Text("Thêm", fontSize = 18.sp)
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
            Text("Chưa có sinh viên nào.", color = Color.Gray)
            Text("Hãy qua tab 'Sinh viên' để thêm mới.", color = Color.Gray)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
