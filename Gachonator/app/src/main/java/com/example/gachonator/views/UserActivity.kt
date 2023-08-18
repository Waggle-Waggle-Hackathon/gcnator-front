package com.example.gachonator.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.gachonator.ApiService
import com.example.gachonator.MyApplication
import com.example.gachonator.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBinding

    var major = ""
    var stdId = ""
    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
    }

    override fun onResume() {
        class myTextWatcher(var id: Int) : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable?) {
                when(id){
                    1 -> {
                        major = binding.userMajorEnterEt.text.toString()
                    }
                    2 -> {
                        stdId = binding.userIdEnterEt.text.toString()
                    }
                    3 -> {
                        name = binding.userNameEnterEt.text.toString()
                    }
                    else -> Log.d("User Activity", "Input Error")
                }

                Log.d("major stdId name", major + " " + stdId + " " + name)

                // 세개 다 입력값 있으면 버튼 활성화
                if (isValidMajor() && isValidId() && name!=""){
                    binding.userNextBtnOffTv.visibility = View.GONE
                    binding.userNextBtnOnTv.visibility = View.VISIBLE
                } else {
                    binding.userNextBtnOffTv.visibility = View.VISIBLE
                    binding.userNextBtnOnTv.visibility = View.GONE
                }
            }

        }

        // 학과 EditText listen
        var mWatcher = myTextWatcher(1)
        binding.userMajorEnterEt.addTextChangedListener(mWatcher)

        // 학번 EditText listen
        var iWatcher = myTextWatcher(2)
        binding.userIdEnterEt.addTextChangedListener(iWatcher)

        // 이름 EditText listen
        var nWatcher = myTextWatcher(3)
        binding.userNameEnterEt.addTextChangedListener(nWatcher)

        initListener()

        super.onResume()
    }

    private fun initListener() {
        binding.userNextBtnOnTv.setOnClickListener {

            /** 서버에 결과 전송 */
            val apiService = MyApplication.instance.sRetrofit.create(ApiService::class.java)

            startActivity(Intent(this, QuestionActivity::class.java))
        }
    }
    private fun isValidMajor() : Boolean{
        val regexFirst = ".*학과$".toRegex()
        val regexSecond = ".*학부$".toRegex()

        val userInput = binding.userMajorEnterEt.text.toString()

        return regexFirst.matches(userInput) || regexSecond.matches(userInput) || userInput.length in 3..20
    }
    private fun isValidId(): Boolean {
        return binding.userIdEnterEt.text.toString().matches(Regex("^[0-9]{9}$"))
    }
}