package com.example.myapplication.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.NewsListViewAdapter
import com.example.myapplication.R
import com.example.myapplication.listItem.NewsListItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // 탭 버튼을 클릭하면 해당하는 탭 화면을 띄움
    lateinit var TABS: List<View> // 탭 화면들
    lateinit var btn_tabs: List<Button> // 탭 버튼들


    var newsItems = mutableListOf<NewsListItem>() // 1번 탭에서 리스트에 보여줄 NewsListItem 데이터 리스트
    val companies = listOf("Naver", "Kakao", "Line", "Coupang", "BaeMin") // 5
    val addItem = { lst: MutableList<NewsListItem>, i: Int ->  // 클릭시 newsItems에 새로운 항목 추가
        lst.add(
            0,
            NewsListItem(
                img_res = R.drawable.ic_launcher_background,
                title = "제목 $i",
                content = "내용 내용 내용",
                company = companies[i % 5],
                date = "2021-${(i % 12 + 1)}-${i % 30 + 1}"
            )
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TABS = listOf(listview_news, tv_empty)
        btn_tabs = listOf(btn_tab1, btn_tab2, btn_tab3, btn_tab4)

        // 탭 버튼 누르면 열릴 탭 화면 지정
        for (idx in btn_tabs.indices) {
            btn_tabs[idx].setOnClickListener { tabChange(idx) }
        }
        for (i in 0 until 100) {
            newsItems.add(
                0,
                NewsListItem(
                    img_res = R.drawable.ic_launcher_background,
                    title = "제목 ${i + 1}",
                    content = "내용 내용 내용",
                    company = companies[i % 5],
                    date = "2021-${(i % 12 + 1)}-${i % 30 + 1}"
                )
            )
        }

        val newsListViewAdapter = NewsListViewAdapter(newsItems)
        listview_news.adapter = newsListViewAdapter

        var i = 101
        btn_add.setOnClickListener {
            addItem.invoke(newsItems, i++)
            newsListViewAdapter.notifyDataSetChanged()
            listview_news.setSelection(0) // 맨 위로 이동
            listview_news.smoothScrollBy(0,0) // 스크롤 멈춤
        }

    }


    // 모든 탭들 invisible로 바꾸고
    // 선택한 탭만 visible로 전환
    fun tabChange(pos: Int) {
        if (pos >= TABS.size)
            return

        for (tabs in TABS) {
            tabs.visibility = View.INVISIBLE
        }

        TABS[pos].visibility = View.VISIBLE
    }
}