package com.example.mltradix.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mltradix.NewsModel
import com.example.mltradix.R

class NewsAdapter(
    var mutableList: MutableList<NewsModel>,
    var callBack: CallBack
) : RecyclerView.Adapter<NewsAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.new_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val news: NewsModel = mutableList[position]
        holder.name.text = news.name
        holder.date.text = news.date
        holder.description.text = news.description
        holder.movement.text = news.movement
        holder.image.setImageResource(news.resourceId)

        holder.layout.setOnClickListener {
            callBack.onClickMess(position, news)
        }
    }

    interface CallBack {
        fun onClickMess(int: Int, messModel: NewsModel)
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var layout: ConstraintLayout = view.findViewById(R.id.layout)
        var name: TextView = view.findViewById(R.id.name)
        var date: TextView = view.findViewById(R.id.date)
        var movement: TextView = view.findViewById(R.id.movement)
        var description: TextView = view.findViewById(R.id.description)
        var image: ImageView = view.findViewById(R.id.image)

    }
}


