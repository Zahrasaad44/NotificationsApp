package com.example.notificationsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notificationsapp.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    lateinit var binding: ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}