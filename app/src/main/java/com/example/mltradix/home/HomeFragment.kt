package com.example.mltradix.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mltradix.R
import com.example.mltradix.adapter.TitleAdapter
import com.example.mltradix.adapter.TitleSecondAdapter
import com.example.mltradix.TitleSecondModel


class HomeFragment : Fragment(), TitleAdapter.CallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var mutableList: MutableList<String>
    private lateinit var titleAdapter: TitleAdapter

    private lateinit var secondRecyclerView: RecyclerView
    private lateinit var secondMutableList: MutableList<TitleSecondModel>
    private lateinit var secondTitleAdapter: TitleSecondAdapter
    var touchHelper : ItemTouchHelper? = null

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_1)
        mutableList = mutableListOf()
        mutableList.add("INDEX")
        mutableList.add("SHARES")
        mutableList.add("CURRENCIES")
        mutableList.add("FUTURES")
        mutableList.add("CRYPTO")
        titleAdapter =
            TitleAdapter(mutableList, this,0,1)
        var staggeredGridLayoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.adapter = titleAdapter

        secondRecyclerView = view.findViewById(R.id.recycler_view_2)
        secondMutableList = mutableListOf()
        var item1 = TitleSecondModel("DOWN JONES","NYSE","10:44:45","20.047,50","+203 (+1,04%)")
        var item2 = TitleSecondModel("FTSE 100","LONDON","10:44:45","20.047,50","+203 (+1,04%)")
        var item3 = TitleSecondModel("IBEX 35","MADRID","10:44:45","20.047,50","+203 (+1,04%)")
        var item4 = TitleSecondModel("DAX","XETRA","10:44:45","20.047,50","+203 (+1,04%)")
        secondMutableList.add(item1)
        secondMutableList.add(item2)
        secondMutableList.add(item3)
        secondMutableList.add(item4)
        secondMutableList.add(item1)
        secondMutableList.add(item2)
        secondMutableList.add(item3)
        secondMutableList.add(item4)
        secondMutableList.add(item1)
        secondMutableList.add(item2)
        secondMutableList.add(item3)
        secondMutableList.add(item4)

        var staggeredGridLayoutManager2 = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)
        secondTitleAdapter =
            TitleSecondAdapter(secondMutableList)
        secondRecyclerView.layoutManager = staggeredGridLayoutManager2
        secondRecyclerView.adapter = secondTitleAdapter


    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onClickMess(int: Int, messModel: String) {
//        titleAdapter.index = int
//        titleAdapter.notifyDataSetChanged()
        titleAdapter =
            TitleAdapter(mutableList, this, int,1)
        var staggeredGridLayoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.adapter = titleAdapter
    }
}