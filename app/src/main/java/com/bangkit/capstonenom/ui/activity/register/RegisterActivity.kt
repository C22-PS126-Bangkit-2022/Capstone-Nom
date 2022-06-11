package com.bangkit.capstonenom.ui.activity.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.bangkit.capstonenom.databinding.ActivityRegisterBinding
import com.bangkit.capstonenom.ui.activity.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.tvSignIn.setOnClickListener{
            val intent = Intent (this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener{
            val username = binding.edtUsername.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            if (username.isEmpty()){
                binding.edtUsername.error = "Name cannot be empty"
                binding.edtUsername.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()){
                binding.edtEmail.error = "Email is required"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmail.error = "Invalid Email"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.edtPassword.error = "Password is required"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6){
                binding.edtPassword.error = "Password must be more than 6 characters"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }
            register(username, email, password)
        }
    }

    private fun register(username: String, email: String, password: String) {
        showLoading(true)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Successful registration", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}