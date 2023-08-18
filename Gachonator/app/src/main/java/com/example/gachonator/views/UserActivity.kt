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
import java.time.LocalDate

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
                if (isValidMajor() && isValidId() && name!=""){ // 활성화
                    binding.userMajorValidTextTv.visibility = View.INVISIBLE
                    binding.userIdValidTextTv.visibility = View.INVISIBLE
                    binding.userNextBtnOffTv.visibility = View.GONE
                    binding.userNextBtnOnTv.visibility = View.VISIBLE
                    Log.d("Validity", "Valid")
                } else {
                    binding.userMajorValidTextTv.visibility = View.INVISIBLE
                    binding.userIdValidTextTv.visibility = View.INVISIBLE
                    binding.userNextBtnOffTv.visibility = View.VISIBLE
                    binding.userNextBtnOnTv.visibility = View.GONE

                    if (major != "" && !isValidMajor()){
                        binding.userMajorValidTextTv.visibility = View.VISIBLE
                        Log.d("major Validity", "inValid")
                    } else{
                        binding.userMajorValidTextTv.visibility = View.INVISIBLE
                        Log.d("major Validity", "major empty")
                    }

                    if (stdId != "" && !isValidId()){
                        binding.userIdValidTextTv.visibility = View.VISIBLE
                        Log.d("id Validity", "inValid")
                    } else{
                        binding.userIdValidTextTv.visibility = View.INVISIBLE
                        Log.d("id Validity", "id empty")
                    }
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

        return regexFirst.matches(userInput) || regexSecond.matches(userInput) && userInput.length in 3..20
    }
    private fun isValidId(): Boolean {
        val userInput = binding.userIdEnterEt.text.toString()
        var year = true

        if (userInput.length == 9 && userInput.substring(0 until 4).toInt() > LocalDate.now().year){
            year = false
        }

        return year && userInput.matches(Regex("^[0-9]{9}$"))
    }
}