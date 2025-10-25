import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

import androidx.lifecycle.viewmodel.compose.viewModel

import com.uth.baitapthuviensach.Model.Student
import com.uth.baitapthuviensach.screen.BookListScreen
import com.uth.baitapthuviensach.screen.BorrowBookDialog
import com.uth.baitapthuviensach.screen.ManagementScreen
import com.uth.baitapthuviensach.screen.StudentListScreen

@Composable
fun LibraryApp() {
    val viewModel: LibraryViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val currentStudent = uiState.students.find { it.id == uiState.currentStudentId }
        ?: Student("", "Không có sinh viên", emptyList())

    val availableBooks = uiState.books.filterNot { b ->
        currentStudent.borrowedBooks.any { it.id == b.id }
    }

    Scaffold(
        bottomBar = {
            AppBottomNavigation(
                selectedIndex = uiState.selectedBottomNavIndex,
                onSelectIndex = { viewModel.selectBottomNav(it) }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (uiState.selectedBottomNavIndex) {
                0 -> ManagementScreen(
                    student = currentStudent,
                    onChangeStudent = { viewModel.changeStudent() },
                    onAddBook = { viewModel.showBorrowBookDialog(true) },
                    hasStudents = uiState.students.isNotEmpty(),
                    onReturnBook = { book -> viewModel.returnBook(book.id) }
                )
                1 -> BookListScreen(
                    books = uiState.books,
                    onAddBookToCatalog = { viewModel.addBookToCatalog(it) }
                )
                2 -> StudentListScreen(
                    students = uiState.students,
                    onAddStudentWithBooks = { name, books -> viewModel.addStudentWithBooks(name, books) },
                    allBooks = uiState.books,
                    onDeleteStudent = { id -> viewModel.deleteStudent(id) }
                )
            }
        }

        if (uiState.showBorrowBookDialog) {
            BorrowBookDialog(
                availableBooks = availableBooks,
                onDismiss = { viewModel.showBorrowBookDialog(false) },
                onBookSelected = { viewModel.borrowBook(it) }
            )
        }
    }
}

