package com.example.mltradix.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mltradix.R
import com.example.mltradix.TitleSecondModel

class TitleSecondAdapter(
    var mutableList: MutableList<TitleSecondModel>
//    var callBack: CallBack
) : RecyclerView.Adapter<TitleSecondAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.title_item_2, parent, false)
            return Holder(view)
    }

    override fun getItemViewType(position: Int): Int {
        if (position == mutableList.size - 1) return 0
        else return 1
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        val mess: TitleSecondModel = mutableList[position]
        holder.name.text = mess.name + "-" +position
        holder.code.text = mess.code
        holder.time.text = mess.time
        holder.price.text = mess.price
        holder.movement.text = mess.movement

    }

    interface CallBack {
        fun onClickMess(int: Int, messModel: String)
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.text_1)
        var code: TextView = view.findViewById(R.id.text_2)
        var time: TextView = view.findViewById(R.id.text_3)
        var price: TextView = view.findViewById(R.id.text_4)
        var movement: TextView = view.findViewById(R.id.text_5)

    }
}


