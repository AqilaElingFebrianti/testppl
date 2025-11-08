package com.example.digicycle.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.digicycle.databinding.ActivityLoginBinding
import com.example.digicycle.models.LoginResponse
import com.example.digicycle.network.RetrofitClient
import com.example.digicycle.repository.AuthRepository
import com.example.digicycle.ui.dashboard.MainDashboardActivity
import com.example.digicycle.ui.register.RegisterActivity
import com.example.digicycle.util.UserManager
import com.example.digicycle.ui.dashboard_admin.AdminDashboardActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(AuthRepository(RetrofitClient.apiService))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionListeners()
        observeViewModel()
    }

    private fun setupActionListeners() {
        binding.btnLogin.setOnClickListener {
            val identifier = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (identifier.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(identifier, password)
            }
        }

        binding.tvForgotPassword.setOnClickListener {
            showContactAdminDialog()
        }

        binding.tvRegisterNow.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {
        viewModel.loginResult.observe(this) { response: LoginResponse? ->
            response?.let {
                UserManager(this).saveUser(
                    token = it.token,
                    userId = it.user_id,
                    fullName = it.full_name,
                    role = it.role,
                    isRemembered = false
                )

                Toast.makeText(this, "Selamat datang, ${it.full_name}!", Toast.LENGTH_SHORT).show()

                val destinationActivity = when (it.role) {
                    "admin" -> AdminDashboardActivity::class.java
                    else -> MainDashboardActivity::class.java
                }

                val intent = Intent(this, destinationActivity)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }

        viewModel.errorMessage.observe(this) { errorMsg ->
            errorMsg?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnLogin.isEnabled = !isLoading
            binding.etEmail.isEnabled = !isLoading
            binding.etPassword.isEnabled = !isLoading
        }
    }

    private fun showContactAdminDialog() {
        AlertDialog.Builder(this)
            .setTitle("Lupa Kata Sandi")
            .setMessage("Silakan hubungi Administrator untuk mereset kata sandi Anda.")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
