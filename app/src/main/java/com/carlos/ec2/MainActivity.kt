package com.carlos.ec2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.carlos.ec2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()

    }

    private fun setupViews() {
        binding.tilEmail.editText?.addTextChangedListener { text ->
            binding.botonlogin.isEnabled = validateInputs(text.toString(), binding.tilPassword.editText?.text.toString())
        }
        binding.tilPassword.editText?.addTextChangedListener { text ->
            binding.botonlogin.isEnabled = validateInputs(binding.tilEmail.editText?.text.toString(), text.toString())
        }
        binding.botonlogin.setOnClickListener {
            val email = binding.tilEmail.editText?.text.toString()
            val password = binding.tilPassword.editText?.text.toString()

            if (email == "ejemplo@idat.edu.pe" && password == "123456") {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this, "Ingreso exitoso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        val isEmailOK = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordOk = password.length >= 6
        return isPasswordOk && isEmailOK
    }
}