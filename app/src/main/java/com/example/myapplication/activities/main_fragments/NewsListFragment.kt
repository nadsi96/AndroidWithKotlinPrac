package com.example.myapplication.activities.main_fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.NewsListViewAdapter
import com.example.myapplication.R
import com.example.myapplication.listItem.NewsListItem
import kotlinx.android.synthetic.main.news_list_fragment.*

class NewsListFragment : Fragment() {

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
    var idx = 100 // 리스트 항목에 사용할 번호

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        for (i in 0 until 100) {
            newsItems.add(
                0,
                NewsListItem(
                    img_res = R.drawable.ic_launcher_background,
                    title = "제목 ${i}",
                    content = "내용 내용 내용",
                    company = companies[i % 5],
                    date = "2021-${(i % 12 + 1)}-${i % 30 + 1}"
                )
            )
        }

        return inflater.inflate(R.layout.news_list_fragment, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val newsListViewAdapter = NewsListViewAdapter(newsItems)
        listview_news.adapter = newsListViewAdapter

        btn_add.setOnClickListener {
            addItem.invoke(newsItems, idx++)
            newsListViewAdapter.notifyDataSetChanged()
            listview_news.setSelection(0) // 맨 위로 이동
            listview_news.smoothScrollBy(0, 0) // 스크롤 멈춤
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i("Fragment_NewsList", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Fragment_NewsList", "onStop")
    }

    // 화면으로 다시 돌아오면, 스크롤을 맨 위로 이동
    override fun onResume() {
        super.onResume()
        listview_news.setSelection(0)
        Log.i("Fragment_NewsList", "onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Fragment_NewsList", "onStart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Fragment_NewsList", "onDestroy")
    }
}
