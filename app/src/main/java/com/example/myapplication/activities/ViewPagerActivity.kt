package com.example.myapplication.activities

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_viewpager.*

class ViewPagerActivity : AppCompatActivity() {

    val tabList = listOf(
        "뉴스 리스트",
        "shape.xml",
        "대화상자, 팝업상자"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)

        Log.i("MainViewPager", "viewPager")
        viewPager.adapter = ViewPagerAdapter(this)

        // 탭 설정
        TabLayoutMediator(
            tabs,
            viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.setCustomView(TextView(this).apply {
                    text = tabList[position]
                    textSize = 18f
                    setTextColor(Color.BLACK)
                    gravity = Gravity.CENTER
                })
            }).attach()
    }
}