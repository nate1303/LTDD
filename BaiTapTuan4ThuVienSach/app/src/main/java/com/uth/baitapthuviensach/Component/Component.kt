package com.uth.baitapthuviensach.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.uth.baitapthuviensach.Model.Book
import kotlin.collections.forEach

@Composable
fun StudentInfoSection(studentName: String, onChangeClick: () -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        Text("Sinh viên", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = studentName,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = onChangeClick) { Text("Thay đổi") }
        }
    }
}

@Composable
fun EmptyBookListMessage() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text("Chưa mượn quyển sách nào", color = Color.Gray)
    }
}


@Composable
fun BorrowedBookListSection(books: List<Book>, onReturnBook: (Book) -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        Text("Danh sách sách đã mượn", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        if (books.isEmpty()) {
            EmptyBookListMessage()
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                books.forEach { book ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("• ${book.title}")
                        Button(
                            onClick = { onReturnBook(book) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF5350))
                        ) {
                            Text("Trả", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}