package com.example.mltradix.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mltradix.MenusModel
import com.example.mltradix.R

class MenusAdapterTest(
    var mutableList: MutableList<MenusModel>,
    var callBack: CallBack,
    var typeInput: Int
) : RecyclerView.Adapter<MenusAdapterTest.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        return when (viewType) {
            0 -> {
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
                Holder(view)
            }
            else -> {
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.menu_item_2, parent, false)
                Holder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return typeInput
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item: MenusModel = mutableList[position]

        holder.title.text = item.title
        holder.image.setImageResource(item.resourceId)

        holder.layout.setOnClickListener {
            callBack.onClickMenuItem(position, item)
        }

    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var layout: ConstraintLayout = view.findViewById(R.id.layout)
        var title: TextView = view.findViewById(R.id.title)
        var image: ImageView = view.findViewById(R.id.image)
    }

    interface CallBack {
        fun onClickMenuItem(int: Int, messModel: MenusModel)
    }
}



//class Holder2(view: View) : RecyclerView.ViewHolder(view) {
//    fun bind(item: MenusModel) {
//        var layout: ConstraintLayout = itemView.findViewById(R.id.layout)
//        var name: TextView = itemView.findViewById(R.id.name)
//        var image: ImageView = itemView.findViewById(R.id.image)
//        name.text = item.name
//        image.setImageResource(item.resourceId)
//
//    }
//}



