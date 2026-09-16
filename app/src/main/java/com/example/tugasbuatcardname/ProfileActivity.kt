package com.example.tugasbuatcardname

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)
        val tvWebsite = findViewById<TextView>(R.id.tvWebsite)

        val btnWebsite = findViewById<Button>(R.id.btnWebsite)
        val btnCall = findViewById<Button>(R.id.btnCall)
        val btnShare = findViewById<Button>(R.id.btnShare)

        // Ambil Data dari Explicit Intent
        val name = intent.getStringExtra("EXTRA_NAME") ?: ""
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        val phone = intent.getStringExtra("EXTRA_PHONE") ?: ""
        var website = intent.getStringExtra("EXTRA_WEBSITE") ?: ""

        // Tampilkan ke View
        tvName.text = name
        tvEmail.text = email
        tvPhone.text = phone
        tvWebsite.text = website

        // 1. Implicit Intent: Kunjungi Website (Browser)
        btnWebsite.setOnClickListener {
            if (!website.startsWith("http://") && !website.startsWith("https://")) {
                website = "https://$website"
            }
            val openBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(website))
            startActivity(openBrowser)
        }

        // 2. Implicit Intent: Hubungi Saya (Dial Pad)
        btnCall.setOnClickListener {
            val openDialer = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
            startActivity(openDialer)
        }

        // 3. Implicit Intent: Bagikan Kartu (Share Sheet / WhatsApp / Email / Notes)
        btnShare.setOnClickListener {
            val shareText = """
                *Kartu Nama Digital*
                Nama: $name
                Email: $email
                No. HP: $phone
                Website: $website
            """.trimIndent()

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            startActivity(Intent.createChooser(shareIntent, "Bagikan Profil Melalui:"))
        }
    }
}