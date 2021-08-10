package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
//            startActivity(Intent(this, MainActivity::class.java))
            startActivity(Intent(this, ViewPagerActivity::class.java))
        }
        btn_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("loginActivity", "onStart()")
    }
    override fun onPause() {
        super.onPause()
        Log.i("loginActivity", "onPause()")
    }
    override fun onStop() {
        super.onStop()
        Log.i("loginActivity", "onStop()")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("loginActivity", "onRestart()")
    }
}