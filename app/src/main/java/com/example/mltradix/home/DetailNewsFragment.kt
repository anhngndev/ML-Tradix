package com.example.mltradix.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mltradix.R
import com.example.mltradix.adapter.TitleAdapter


class DetailNewsFragment : Fragment(), TitleAdapter.CallBack {


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
        return inflater.inflate(R.layout.fragment_detail_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var mutableList: MutableList<String>
    private lateinit var titleAdapter: TitleAdapter
    lateinit var titleNew: TextView
    lateinit var title: String
    private fun initView(view: View) {
        titleNew = view.findViewById(R.id.title_new)
        recyclerView = view.findViewById(R.id.recycler_view_1)
        mutableList = mutableListOf()
        mutableList.add("EDITORIAL")
        mutableList.add("CRYPTO NEWS")
        mutableList.add("RAW MATERIAL")
        mutableList.add("ECONOMICS")
        titleAdapter =
            TitleAdapter(mutableList, this, 0, 2)
        var staggeredGridLayoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.adapter = titleAdapter

        var args = this.arguments
        title = args?.getString("title", "")!!
        titleNew.text = title
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            DetailNewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClickMess(int: Int, messModel: String) {

        titleAdapter =
            TitleAdapter(mutableList, this, int, 2)
        var staggeredGridLayoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.adapter = titleAdapter
    }
}