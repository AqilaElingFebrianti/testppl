package com.example.digicycle.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.digicycle.databinding.FragmentLoginBinding
import com.example.digicycle.ui.register.RegisterActivity //

class LoginFragment : Fragment() {
    // ... konten lainnya tetap sama
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            // Menggunakan Intent untuk pindah dari Fragment ke Activity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            // Tidak perlu finish() karena MainActivity tetap ada di stack
        }

        // ✅ Implementasi: Tombol 'Daftar' meluncurkan RegisterActivity
        // ID tombol di fragment_login.xml adalah @+id/btn_register
        binding.btnRegister.setOnClickListener {
            // Menggunakan Intent untuk pindah dari Fragment ke Activity
            val intent = Intent(requireContext(), RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}