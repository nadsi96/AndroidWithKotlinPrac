package com.example.myapplication.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.CheckRegisterData
import com.example.myapplication.R
import com.example.myapplication.data.RegisterData
import com.example.myapplication.thread.RegisterVerifyTimerThread
import kotlinx.android.synthetic.main.activity_register.*

enum class Colors(val rgb: String) {
    CLICKED("#257CC1"),
    UNCLICKED("#ffffff"),
    CLICKED_TEXT("#ffffff"),
    UNCLICKED_TEXT("#000000");

}

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var msg: Toast
    var selectedGender: Int = -1

    lateinit var requiredItems: Map<String, EditText>

    
    val handler = object: Handler(RegisterVerifyTimerThread){
//        override fun handleMessage(msg: Message){
            Log.i("get Message", "${msg.what}")
            when(msg.what){
                0 -> {
                    tv_verifyTimeLimit.text = "${msg.arg1} 초"
                }
                1 -> {
                    tv_verifyTimeLimit.text = "시간초과"
                    btn_check.isEnabled = false
                }
            }
        }
    }

    val runnable = Runnable {

    }
    val timeOverThread = object: Thread(){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        msg = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)

        tv_male.setOnClickListener(this)
        tv_female.setOnClickListener(this)
        btn_register.setOnClickListener(this)
        btn_verify.setOnClickListener(this)
        btn_check.setOnClickListener(this)

        // 필수 입력 정보
        requiredItems = mapOf(
            "id" to et_id,
            "pw" to et_pw,
            "이름" to et_name,
            "생년" to et_birthYear,
            "생월" to et_birthMonth,
            "생일" to et_birthDate,
            "비상용 이메일" to et_tempEmail,
            "전화번호" to et_phone,
            "인증번호" to et_verifyNum
        )
    }

    override fun onClick(v: View?) {
        val item = v?.id
        when (item) {
            R.id.tv_male -> {
                changeColor(tv_male, tv_female)
                selectedGender = 0
            }
            R.id.tv_female -> {
                changeColor(tv_female, tv_male)
                selectedGender = 1
            }
            R.id.btn_register -> {
                Log.i("register button", "clicked")

                // 입력되는 EditText의 내용이 빈칸인지 확인
                val getString = { et: EditText ->
                    if (et.text.toString().length > 0) et.text.toString() else null
                }

                // 비밀번호 일치 확인
                if (et_pw.text.toString() != et_pwCheck.text.toString()) {
                    Log.i("password", et_pw.text.toString())
                    Log.i("password check", et_pwCheck.text.toString())
                    Toast.makeText(applicationContext, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT)
                        .show()
                    return
                }

                // 입력 정보 중, 입력되지 않은 내용 확인
                for ((itemKey, itemValue) in requiredItems.entries) {
                    Log.i("itemKey", itemKey)
                    if (itemValue.text.toString().isEmpty()) {
                        Toast.makeText(applicationContext, "${itemKey} 미입력", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }
                }

                // 성별 미선택여부 확인
                if (selectedGender == -1) {
                    Toast.makeText(applicationContext, "성별 미선택", Toast.LENGTH_SHORT).show()
                    return
                }

                val data = RegisterData(
                    getString(et_id),
                    getString(et_pw),
                    getString(et_name),
                    selectedGender,
                    getString(et_birthYear)?.toInt(),
                    getString(et_birthMonth)?.toInt(),
                    getString(et_birthDate)?.toInt(),
                    getString(et_tempEmail),
                    getString(et_phone)
                )

                val bundle = Bundle().apply {
                    putParcelable("data", data)
                }
                Log.i("Activity_Register", bundle.toString())

                val intent = Intent(this, CheckRegisterData::class.java).apply {
                    putExtra("bundle", bundle)

                }

                startActivity(intent)

            }
            R.id.btn_verify ->{
                Toast.makeText(this, "인증번호를 전송했습니다", Toast.LENGTH_SHORT).show()
                // 확인버튼 클릭시
                // 재전송으로 텍스트 변경
                // 인증버튼 활성화
                // 타이머 textview 표시
                // 타이머 스레드 실행
                btn_verify.text = "재전송"
                btn_check.setEnabled(true)
                tv_verifyTimeLimit.visibility = View.VISIBLE
                RegisterVerifyTimerThread(handler).run()
            }
            R.id.btn_check -> {

            }
            else -> return
        }
    }

    private fun changeColor(tv1: TextView, tv2: TextView) {
        tv1.setBackgroundColor(Color.parseColor(Colors.CLICKED.rgb))
        tv1.setTextColor(Color.parseColor(Colors.CLICKED_TEXT.rgb))
        tv2.setBackgroundColor(Color.parseColor(Colors.UNCLICKED.rgb))
        tv2.setTextColor(Color.parseColor(Colors.UNCLICKED_TEXT.rgb))
    }

}
