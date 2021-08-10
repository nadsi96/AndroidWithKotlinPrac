package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.activities.LoginActivity
import com.example.myapplication.data.RegisterData
import kotlinx.android.synthetic.main.activity_check_register_data.*

class CheckRegisterData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_register_data)

        val intent = intent
        Log.i("Intent", if(intent!=null) "1" else "2")
        val bundle = intent?.getBundleExtra("bundle")
        Log.i("bundle", bundle.toString())

        if (bundle != null) {
            val data = bundle.getParcelable<RegisterData>("data")
            data?.run {
                tv_id.text = this.id
                tv_pw.text = this.pw
                tv_name.text = this.name
                tv_gender.text = this.getGenderToString()
                tv_birthday.text = this.getBirthdayToString()
                tv_email.text = this.email
                tv_phone.text = this.phoneNum
            }
        }

        btn_confirm.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java).apply{
                this.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP //액티비티 스택제거
                    // 이전에 열린 엑티비티들 종료

            })
            finish()
        }
    }
}