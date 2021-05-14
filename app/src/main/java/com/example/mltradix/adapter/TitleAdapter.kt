package com.example.mltradix.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mltradix.R

class TitleAdapter(
    var mutableList: MutableList<String>,
    var callBack: CallBack,
    var index: Int,
    var background: Int

) : RecyclerView.Adapter<TitleAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.title_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val mess: String = mutableList[position]
        holder.title.text = mess

        if (index == position) {
            when (background) {
                1 -> {
                    holder.title.setBackgroundResource(R.drawable.border_12)
                    holder.title.setTextColor(Color.parseColor("#FFFFFFFF"))
                }
                2 -> {
                    holder.title.setBackgroundResource(R.drawable.border_13_green)
                    holder.title.setTextColor(Color.parseColor("#FF000000"))
                }
            }
        }

        holder.title.setOnClickListener {
            index = position
            callBack.onClickMess(position, mess)
        }

    }

    interface CallBack {
        fun onClickMess(int: Int, messModel: String)
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
    }
}


