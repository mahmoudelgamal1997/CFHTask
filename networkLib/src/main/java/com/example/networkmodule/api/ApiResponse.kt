package com.example.netoworklib.api

data class ApiResponse(
    val success: Boolean? = null,
    val message: String? = null,
    val data: Any? = null
)