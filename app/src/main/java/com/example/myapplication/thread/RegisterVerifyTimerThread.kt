package com.example.myapplication.thread

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

// 회원가입시 휴대전화번호 인증 타이머
class RegisterVerifyTimerThread(val handler: Handler): Thread() {

    init{
        super.setDaemon(true)
    }

    lateinit var msg: Message // handler에 보낼 메시지

    override fun run() {
//        super.run()
        var countDown = 30 // 시간제한 30초
        Looper.prepare()

        // 0초가 될 때까지
        // 초마다 handler에 남은시간 보냄
        while(countDown > 0){
            Log.i("Thread", "running $countDown")
            sleep(1000)
            countDown -= 1
            msg = Message.obtain()
            msg.what = 1
            msg.arg1 = countDown
            handler.sendMessage(msg)
        }

        // 시간이 종료된 후
        handler.sendMessage(Message.obtain(handler, 0))
    }
}