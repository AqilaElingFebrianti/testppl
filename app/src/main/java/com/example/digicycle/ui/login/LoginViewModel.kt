package com.example.digicycle.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

// 👇 PERBAIKAN: Import dari 'digicycle'
import com.example.digicycle.models.LoginResponse
import com.example.digicycle.repository.AuthRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

class LoginViewModel(private val repo: AuthRepository) : ViewModel() {

    val loginResult = MutableLiveData<LoginResponse?>()
    val errorMessage = MutableLiveData<String?>()
    val isLoading = MutableLiveData<Boolean>()

    fun login(identifier: String, password: String) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = repo.login(identifier, password)
                if (response.isSuccessful) {
                    loginResult.value = response.body()
                } else {
                    val errorBody = response.errorBody()?.string()
                    if (errorBody != null) {
                        try {
                            val jsonObject = JSONObject(errorBody)
                            val message = jsonObject.getString("message")
                            errorMessage.value = message
                        } catch (e: Exception) {
                            errorMessage.value = "Login gagal: ${response.code()}"
                        }
                    } else {
                        errorMessage.value = "Login gagal: ${response.message()}"
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = "Tidak dapat terhubung ke server. Cek koneksi internet."
            } finally {
                isLoading.value = false
            }
        }
    }
}