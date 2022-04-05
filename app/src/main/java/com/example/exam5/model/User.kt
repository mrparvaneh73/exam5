package com.example.exam5.model

data class User(
    val _id: String="-1",
    val firstName: String,
    val lastName: String,
    val nationalCode: String,
    val hobbies: List<String>
)
