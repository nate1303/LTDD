package com.uth.baitapthuviensach.screen

import android.R.attr.name
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.uth.baitapthuviensach.Model.Book

@Composable
fun AddStudentDialog(
    allBooks: List<Book>,
    onConfirm: (String, List<Book>) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    val selectedBooks = remember { mutableStateListOf<Book>() }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Thêm sinh viên mới") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Tên sinh viên") },
                    modifier = Modifier.fillMaxWidth()
                )

            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(name, selectedBooks.toList()) }) {
                Text("Thêm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Hủy") }
        }
    )
}

// --- COMPONENTS ---
@Composable
fun BorrowBookDialog(
    availableBooks: List<Book>,
    onDismiss: () -> Unit,
    onBookSelected: (Book) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(shape = RoundedCornerShape(16.dp)) {
            Column(Modifier.padding(16.dp)) {
                Text("Chọn sách để mượn", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                if (availableBooks.isEmpty()) Text("Không còn sách để mượn.")
                else LazyColumn {
                    items(availableBooks) { book ->
                        Text(
                            text = book.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onBookSelected(book) }
                                .padding(8.dp)
                        )
                    }
                }
                Spacer(Modifier.height(12.dp))
                TextButton(onClick = onDismiss, modifier = Modifier.align(Alignment.End)) {
                    Text("Đóng")
                }
            }
        }
    }
}
