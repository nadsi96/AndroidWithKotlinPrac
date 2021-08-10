package com.example.myapplication.activities.main_fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.customDialog.CustomDialog
import kotlinx.android.synthetic.main.dialog_popup_fragment.*
import kotlinx.android.synthetic.main.popup_window.view.*

class DialogPopupFragment : Fragment() {

    lateinit var builder: AlertDialog.Builder // 일반 대화상자
    lateinit var customBuilder: CustomDialog // 커스텀 대화상자
    lateinit var popUp: PopupWindow // 팝업상자

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        builder = AlertDialog.Builder(this.context).apply {
            setTitle("제목")
            setMessage("내용")
            setPositiveButton("확인", DialogInterface.OnClickListener { dialog, id ->
                Toast.makeText(this.context, "확인", Toast.LENGTH_SHORT).show()
            })
        }
        builder.create()

        customBuilder = CustomDialog(this.context).apply {
            setView(inflater.inflate(R.layout.custom_alertdialog, null))
        }
        customBuilder.create()

        popUp = PopupWindow(this.context)

        return inflater.inflate(R.layout.dialog_popup_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val yesClick: () -> Unit = {
            Toast.makeText(context, "네", Toast.LENGTH_SHORT).show()
            customBuilder.dismiss()
        }
        val noClick: () -> Unit = {
            Toast.makeText(this.context, "아니요", Toast.LENGTH_SHORT).show()
            customBuilder.dismiss()
        }
        customBuilder.setButton(yesClick, noClick) // 커스텀 대화상자 예, 아니오 버튼 동작 설정


        btn_showDialog.setOnClickListener {
            builder.show()
            popUp.dismiss()
        } // 일반대화상자 실행
        btn_showCustomDialog.setOnClickListener {
            customBuilder.show()
            popUp.dismiss()
        } // 커스텀대화상자 실행

        val view = LayoutInflater.from(this.context).inflate(R.layout.popup_window, null)

        popUp.contentView = view

        btn_showPopup.setOnClickListener {
            popUp.showAsDropDown(btn_showPopup)
            view.img_exit.setOnClickListener { popUp.dismiss() }
        }
        btn_closePopup.setOnClickListener { popUp.dismiss() }

    }

    // 화면 바뀌면 팝업상자 없애기
    override fun onPause() {
        popUp.dismiss()
        super.onPause()
    }


}