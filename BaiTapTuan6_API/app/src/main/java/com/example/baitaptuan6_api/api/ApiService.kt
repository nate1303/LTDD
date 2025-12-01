package com.example.baitaptuan6_api.api

import com.example.baitaptuan6_api.model.ApiResponse
import com.example.baitaptuan6_api.model.Task
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // ✅ Lấy tất cả tasks
    @GET("tasks")
    suspend fun getTasks(): Response<ApiResponse<List<Task>>>

    // ✅ Lấy chi tiết task theo id
    @GET("tasks/{id}")
    suspend fun getTaskDetail(@Path("id") id: Int): Response<ApiResponse<Task>>

    // ✅ Xóa task
    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<ApiResponse<Unit>>

    // ✅ Xóa subtask
    @DELETE("tasks/{taskId}/subtasks/{subtaskId}")
    suspend fun deleteSubtask(
        @Path("taskId") taskId: Int,
        @Path("subtaskId") subtaskId: Int
    ): Response<ApiResponse<Unit>>
}
