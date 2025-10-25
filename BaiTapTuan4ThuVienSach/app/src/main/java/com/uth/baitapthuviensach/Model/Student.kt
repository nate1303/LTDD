package com.uth.baitapthuviensach.Model


data class Student(
    val id: String,
    val name: String,
    val borrowedBooks: List<Book>
)
