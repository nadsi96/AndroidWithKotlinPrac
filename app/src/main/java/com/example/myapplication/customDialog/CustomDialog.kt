package com.example.myapplication.customDialog

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import kotlinx.android.synthetic.main.custom_alertdialog.*

class CustomDialog(context: Context?) : AlertDialog(context) {

    // 예, 아니오 버튼 클릭에 대한 동작 지정
    fun setButton(yesClick: () -> Unit, noClick: () -> Unit) {
        btn_customDialogYes.setOnClickListener {
            this.dismiss()
            yesClick()
        }
        btn_customDialogNo.setOnClickListener {
            this.dismiss()
            noClick()
        }
    }

    override fun create() {
        super.create()
    }
}