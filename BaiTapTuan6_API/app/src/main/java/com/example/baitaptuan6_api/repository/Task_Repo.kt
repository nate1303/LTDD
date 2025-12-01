package com.example.baitaptuan6_api.repository

import com.example.baitaptuan6_api.api.RetrofitInstance
import com.example.baitaptuan6_api.model.Task

class Task_Repo {
    private val api = RetrofitInstance.api

    // ✅ Lấy danh sách tất cả Task
    suspend fun getAllTasks(): List<Task> {
        return try {
            val response = api.getTasks()
            if (response.isSuccessful) {
                response.body()?.data ?: emptyList()
            } else emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    // ✅ Lấy chi tiết 1 Task theo ID
    suspend fun getTaskById(id: Int): Task? {
        return try {
            val response = api.getTaskDetail(id)
            if (response.isSuccessful) {
                val body = response.body()
                when (val data = body?.data) {
                    is Task -> data
                    is List<*> -> data.filterIsInstance<Task>().firstOrNull()
                    else -> null
                }
            } else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    // ✅ Xóa Task
    suspend fun deleteTask(id: Int): Boolean {
        return try {
            val response = api.deleteTask(id)
            response.isSuccessful && (response.body()?.isSuccess ?: false)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    // ✅ Xóa Subtask
    suspend fun deleteSubtask(taskId: Int, subtaskId: Int): Boolean {
        return try {
            val response = api.deleteSubtask(taskId, subtaskId)
            response.isSuccessful && (response.body()?.isSuccess ?: false)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}
