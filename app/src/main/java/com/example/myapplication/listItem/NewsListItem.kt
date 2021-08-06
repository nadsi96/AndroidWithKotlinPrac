package com.example.myapplication.listItem

/*
 * activity_main의 listview_news
 * news_list.item에 표시할 데이터
 */
data class NewsListItem(
    var img_res: Int, // 이미지 resID
    var title: String,// 뉴스 제목
    var content: String,// 뉴스 내용
    var company: String,// 뉴스 발행한 회사명
    var date: String // 기사 발행일
){
}
