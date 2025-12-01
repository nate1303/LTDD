package com.example.baitaptuan6_api.model

data class Subtask(
    val id: Int,
    val title: String,
    val isCompleted: Boolean
)

data class Attachment(
    val id: Int,
    val fileName: String,
    val fileUrl: String
)

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val priority: String,
    val category: String,
    val createdAt: String,
    val dueDate: String,
    val updatedAt: String,
    val subtasks: List<Subtask> = emptyList(),
    val attachments: List<Attachment> = emptyList()
)
