package com.example.gachonator.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gachonator.ApiService
import com.example.gachonator.MyApplication
import com.example.gachonator.databinding.ActivityQuestionBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionActivity : AppCompatActivity() {
    lateinit var binding: ActivityQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()

    }
    private fun initListener() {
        binding.dontKnowBtnTv.setOnClickListener {
            api()
            startActivity(Intent(this, ResultActivity::class.java))
        }
    }
    private fun api() {
        val apiService = MyApplication.instance.sRetrofit.create(ApiService::class.java)
        apiService/*.getSampleData().enqueue(object: Callback<SampleData> {
            override fun onResponse(call: Call<SampleData>, response: Response<SampleData>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    // 데이터 처리
                } else {
                    // API 응답에 실패 (예: 404, 500 등의 서버 에러)
                }
            }

            override fun onFailure(call: Call<SampleData>, t: Throwable) {
                // 네트워크 문제나 데이터 직렬화 오류 등의 기술적 문제 발생 시
            }
        })*/
    }
}