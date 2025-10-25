package com.uth.baitapthuviensach.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uth.baitapthuviensach.Model.Book
import com.uth.baitapthuviensach.Model.Student

@Composable
fun StudentListScreen(
    students: List<Student>,
    onAddStudentWithBooks: (String, List<Book>) -> Unit,
    allBooks: List<Book>,
    onDeleteStudent: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Danh sách sinh viên", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(Modifier.height(16.dp))
        Button(onClick = { showDialog = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Thêm sinh viên mới")
        }
        Spacer(Modifier.height(16.dp))
        if (students.isEmpty()) Text("Chưa có sinh viên nào.")
        else LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(students) { student ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp)) {
                        Text(student.name, fontWeight = FontWeight.Bold)
                        if (student.borrowedBooks.isEmpty())
                            Text("Chưa mượn sách nào.")
                        else
                            Text("Đã mượn: ${student.borrowedBooks.joinToString { it.title }}")
                        Spacer(Modifier.height(8.dp))
                        Button(
                            onClick = { onDeleteStudent(student.id) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
                        ) {
                            Text("Xóa sinh viên", color = Color.White)
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        AddStudentDialog(
            allBooks = allBooks,
            onConfirm = { name, selected ->
                onAddStudentWithBooks(name, selected)
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
}
