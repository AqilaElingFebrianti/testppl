package com.example.digicycle.models

data class LoginResponse(
    val user_id: Int,
    val full_name: String,
    val role: String,
    val token: String,
    val expires_at: String
)