package com.example.traveler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.traveler.databinding.ActivityAboutPageBinding

class AboutPage : AppCompatActivity() {

    private lateinit var binding: ActivityAboutPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutPageBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnAboutBack.setOnClickListener {
            finish()
        }

        binding.btnFollow.setOnClickListener {
            val toast = Toast.makeText(this, "Followed", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}