package com.example.tugasbuatcardname

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etWebsite = findViewById<EditText>(R.id.etWebsite)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            // Explicit Intent mengoper data ke ProfileActivity
            val intent = Intent(this, ProfileActivity::class.java).apply {
                putExtra("EXTRA_NAME", etName.text.toString())
                putExtra("EXTRA_EMAIL", etEmail.text.toString())
                putExtra("EXTRA_PHONE", etPhone.text.toString())
                putExtra("EXTRA_WEBSITE", etWebsite.text.toString())
            }
            startActivity(intent)
        }
    }
}