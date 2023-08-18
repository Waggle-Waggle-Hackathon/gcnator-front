package com.example.gachonator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gachonator.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {
    lateinit var binding: ActivityQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
    }
    private fun initListener() {
        binding
    }
}