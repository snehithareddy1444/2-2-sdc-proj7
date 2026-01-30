package com.example.casestudy1

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.content.Intent
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {

    private var isLogin = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirm = findViewById<EditText>(R.id.etConfirmPassword)
        val btnAction = findViewById<Button>(R.id.btnAction)
        val tvToggle = findViewById<TextView>(R.id.tvToggle)
        val tvSuccess = findViewById<TextView>(R.id.tvSuccess)

        btnAction.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Invalid Email"
                return@setOnClickListener
            }

            if (password.length < 6) {
                etPassword.error = "Password must be 6 characters"
                return@setOnClickListener
            }

            if (!isLogin) {
                val confirm = etConfirm.text.toString()
                if (password != confirm) {
                    etConfirm.error = "Passwords do not match"
                    return@setOnClickListener
                }
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)

                tvSuccess.visibility = View.VISIBLE
                tvSuccess.text = "Welcome $email 🎉"
            }

            tvToggle.setOnClickListener {
                isLogin = !isLogin

                if (isLogin) {
                    tvTitle.text = "Login"
                    btnAction.text = "Login"
                    etConfirm.visibility = View.GONE
                    tvToggle.text = "Don't have an account? Register"
                } else {
                    tvTitle.text = "Register"
                    btnAction.text = "Register"
                    etConfirm.visibility = View.VISIBLE
                    tvToggle.text = "Already have an account? Login"
                }
            }
        }
    }
}

