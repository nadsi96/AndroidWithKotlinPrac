package com.example.myapplication.handler

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterVerifyHandler(l: Looper, val tv_verifyTimeLimit: TextView, val btn_check: Button): Handler(l) {
    override fun handleMessage(msg: Message) {
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