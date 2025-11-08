package com.example.digicycle.repository
import com.example.digicycle.models.LoginRequest
import com.example.digicycle.models.LoginResponse
import com.example.digicycle.network.ApiService
import retrofit2.Response

class AuthRepository(private val api: ApiService) {
    suspend fun login(identifier: String, password: String): Response<LoginResponse> {
        return api.login(LoginRequest(identifier, password))
    }
}