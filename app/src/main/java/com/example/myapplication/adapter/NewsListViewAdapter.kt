package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.listItem.NewsListItem
import kotlinx.android.synthetic.main.news_list_item.view.*

/*
 *
 */
class NewsListViewAdapter(var items: MutableList<NewsListItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): NewsListItem {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // convertView == 재사용할 View
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var itemView = convertView
        var vh: ViewHolder

        if (itemView == null) {
            itemView =
                LayoutInflater.from(parent?.context).inflate(R.layout.news_list_item, parent, false)
            vh = ViewHolder(itemView)

            itemView.tag = vh
        } else {
            vh = itemView.tag as ViewHolder
        }

        val item = getItem(position)
        vh.img.setImageResource(item.img_res)
        vh.title.text = item.title
        vh.contents.text = item.content
        vh.company.text = item.company
        vh.date.text = item.date

        return itemView
    }
}


class ViewHolder (v: View){
    var img: ImageView = v.img_news
    var title: TextView = v.tv_newsTitle
    var contents: TextView = v.tv_newsContent
    var company: TextView = v.tv_newsCompany
    var date: TextView = v.tv_newsDate
}