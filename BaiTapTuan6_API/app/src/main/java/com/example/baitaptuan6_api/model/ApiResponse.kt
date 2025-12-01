

package com.example.baitaptuan6_api.model

data class ApiResponse<T>(
    val isSuccess: Boolean,
    val message: String,
    val data: T
)
