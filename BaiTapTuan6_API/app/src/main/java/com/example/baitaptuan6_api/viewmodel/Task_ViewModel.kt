package com.example.baitaptuan6_api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baitaptuan6_api.model.Task
import com.example.baitaptuan6_api.repository.Task_Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class Task_ViewModel : ViewModel() {

    private val repo = Task_Repo()

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _taskDetail = MutableStateFlow<Task?>(null)
    val taskDetail: StateFlow<Task?> = _taskDetail

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage


    // 🔹 Lấy danh sách tất cả Task
    fun loadTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val list = repo.getAllTasks()
                if (list.isNotEmpty()) {
                    _tasks.value = list
                } else {
                    _errorMessage.value = "Danh sách trống hoặc lỗi API"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Lỗi không xác định"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // 🔹 Lấy chi tiết Task (từ danh sách hiện có)
    fun loadTaskDetail(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                // ✅ Không gọi API riêng — lấy từ danh sách đã có
                val task = _tasks.value.find { it.id == id }
                if (task != null) {
                    _taskDetail.value = task
                } else {
                    // nếu chưa có danh sách (vd mở app trực tiếp vào detail)
                    val list = repo.getAllTasks()
                    val found = list.find { it.id == id }
                    if (found != null) {
                        _taskDetail.value = found
                    } else {
                        _errorMessage.value = "Không tìm thấy task ID: $id"
                    }
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Lỗi không xác định"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            val success = repo.deleteTask(id)
            if (success) {
                _tasks.value = _tasks.value.filterNot { it.id == id }
            } else {
                _errorMessage.value = "Không thể xóa task ID: $id"
            }
        }
    }

    fun deleteSubtask(taskId: Int, subtaskId: Int) {
        viewModelScope.launch {
            val success = repo.deleteSubtask(taskId, subtaskId)
            if (success) {
                _taskDetail.value = _taskDetail.value?.copy(
                    subtasks = _taskDetail.value?.subtasks?.filterNot { it.id == subtaskId } ?: emptyList()
                )
            } else {
                _errorMessage.value = "Không thể xóa subtask ID: $subtaskId"
            }
        }
    }

    fun deleteSelectedSubtasks(taskId: Int, selectedIndexes: List<Int>) {
        viewModelScope.launch {
            val task = _taskDetail.value ?: return@launch
            try {
                val subtaskIds = selectedIndexes.mapNotNull { i -> task.subtasks.getOrNull(i)?.id }
                subtaskIds.forEach { id -> repo.deleteSubtask(taskId, id) }
                _taskDetail.value = task.copy(
                    subtasks = task.subtasks.filterIndexed { i, _ -> i !in selectedIndexes }
                )
            } catch (e: Exception) {
                _errorMessage.value = "Lỗi khi xóa subtasks: ${e.message}"
            }
        }
    }
}
