package com.example.gachonator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gachonator.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
    }
    private fun initListener() {
        binding.userNextBtnTv.setOnClickListener {
            startActivity(Intent(this, QuestionActivity::class.java))
        }
    }
}