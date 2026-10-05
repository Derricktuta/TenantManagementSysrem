package com.example.tenantmanagementsysrem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val registerText = findViewById<TextView>(R.id.tvRegister)
        val helpText = findViewById<TextView>(R.id.helpTextView)

        // Receive email from RegisterActivity
        val registeredEmail = intent.getStringExtra("EMAIL")

        if (registeredEmail != null) {
            email.setText(registeredEmail)
        }

        loginButton.setOnClickListener {

            if (email.text.toString().isEmpty() || password.text.toString().isEmpty()) {

                Toast.makeText(
                    this,
                    "Please enter email and password",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity::class.java)

            // Send email to MainActivity
            intent.putExtra("EMAIL", email.text.toString())

            startActivity(intent)
            finish()
        }

        registerText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        helpText.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.strathmore.edu")
            )
            startActivity(intent)
        }
    }
}