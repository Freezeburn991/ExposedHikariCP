package com.test.testsqlorac

data class User(
    val userId: Int,
    val email: String,
    val displayName: String,
    val passwordHash: String
)