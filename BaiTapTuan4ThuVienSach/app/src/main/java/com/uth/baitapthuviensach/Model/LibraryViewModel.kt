import androidx.lifecycle.ViewModel
import com.uth.baitapthuviensach.Model.Book
import com.uth.baitapthuviensach.Model.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class LibraryViewModel : ViewModel() {
    private val initialBooks = listOf(
        Book(UUID.randomUUID().toString(), "Lập trình Kotlin"),
        Book(UUID.randomUUID().toString(), "Jetpack Compose cơ bản"),
        Book(UUID.randomUUID().toString(), "Cấu trúc dữ liệu & Giải thuật"),
        Book(UUID.randomUUID().toString(), "Thiết kế giao diện Android")
    )

    private val initialStudents = listOf(
        Student(
            UUID.randomUUID().toString(),
            "Nguyễn Văn A",
            listOf(initialBooks[0], initialBooks[1])
        ),
        Student(
            UUID.randomUUID().toString(),
            "Nguyễn Thị B",
            listOf(initialBooks[2])
        )
    )

    private val _uiState = MutableStateFlow(
        LibraryUiState(
            students = initialStudents,
            books = initialBooks,
            currentStudentId = initialStudents.firstOrNull()?.id ?: ""
        )
    )
    val uiState: StateFlow<LibraryUiState> = _uiState.asStateFlow()

    fun selectBottomNav(index: Int) {
        _uiState.update { it.copy(selectedBottomNavIndex = index) }
    }

    fun changeStudent() {
        val students = _uiState.value.students
        if (students.isEmpty()) return
        val currentIndex = students.indexOfFirst { it.id == _uiState.value.currentStudentId }
        val nextIndex = (currentIndex + 1) % students.size
        _uiState.update { it.copy(currentStudentId = students[nextIndex].id) }
    }

    fun showBorrowBookDialog(show: Boolean) {
        _uiState.update { it.copy(showBorrowBookDialog = show) }
    }

    fun borrowBook(book: Book) {
        _uiState.update { current ->
            val updated = current.students.map { st ->
                if (st.id == current.currentStudentId)
                    st.copy(borrowedBooks = st.borrowedBooks + book)
                else st
            }
            current.copy(students = updated, showBorrowBookDialog = false)
        }
    }

    fun addBookToCatalog(title: String) {
        if (title.isBlank()) return
        val newBook = Book(UUID.randomUUID().toString(), title)
        _uiState.update { it.copy(books = it.books + newBook) }
    }

    // Thêm sinh viên với danh sách sách được chọn
    fun addStudentWithBooks(name: String, selectedBooks: List<Book>) {
        if (name.isBlank()) return
        val newStudent = Student(UUID.randomUUID().toString(), name, selectedBooks)
        _uiState.update {
            val newCurrent = if (it.students.isEmpty()) newStudent.id else it.currentStudentId
            it.copy(students = it.students + newStudent, currentStudentId = newCurrent)
        }
    }

    // Xóa sinh viên
    fun deleteStudent(studentId: String) {
        _uiState.update { current ->
            val updatedStudents = current.students.filterNot { it.id == studentId }
            val newCurrentId =
                if (updatedStudents.any { it.id == current.currentStudentId }) current.currentStudentId
                else updatedStudents.firstOrNull()?.id ?: ""
            current.copy(
                students = updatedStudents,
                currentStudentId = newCurrentId
            )
        }
    }

    // Trả sách (xóa sách khỏi borrowedBooks của sinh viên hiện tại)
    fun returnBook(bookId: String) {
        _uiState.update { current ->
            val updatedStudents = current.students.map { student ->
                if (student.id == current.currentStudentId) {
                    val newBooks = student.borrowedBooks.filterNot { it.id == bookId }
                    student.copy(borrowedBooks = newBooks)
                } else student
            }
            current.copy(students = updatedStudents)
        }
    }
}

// --- STATE CLASS ---
data class LibraryUiState(
    val students: List<Student>,
    val books: List<Book>,
    val currentStudentId: String,
    val selectedBottomNavIndex: Int = 0,
    val showBorrowBookDialog: Boolean = false
)
