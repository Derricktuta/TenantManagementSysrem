package com.example.tenantmanagementsysrem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsysrem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastTenant: Tenant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("EMAIL")

        if (email != null) {
            Toast.makeText(
                this,
                "Logged in as $email",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.saveButton.setOnClickListener {

            val name = binding.tenantNameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val rent = binding.rentEditText.text.toString()

            if (name.isEmpty()) {
                binding.tenantNameEditText.error = "Required"
            }

            if (phone.isEmpty()) {
                binding.phoneEditText.error = "Required"
            }

            if (rent.isEmpty()) {
                binding.rentEditText.error = "Required"
            }

            if (name.isEmpty() || phone.isEmpty() || rent.isEmpty()) {
                return@setOnClickListener
            }

            val tenant = Tenant(name, phone, rent)

            binding.tenant = tenant
            lastTenant = tenant

            binding.tenantResultTextView.text = tenant.summary()

            Toast.makeText(
                this,
                "Tenant saved successfully",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.callButton.setOnClickListener {

            val tenant = lastTenant

            if (tenant == null) {

                Toast.makeText(
                    this,
                    "Save a tenant first",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val intent = Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:${tenant.phone}")
            )

            startActivity(intent)
        }

        binding.shareButton.setOnClickListener {

            val tenant = lastTenant

            if (tenant == null) {

                Toast.makeText(
                    this,
                    "Save a tenant first",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, tenant.summary())

            startActivity(Intent.createChooser(intent, "Share tenant details"))
        }
    }
}