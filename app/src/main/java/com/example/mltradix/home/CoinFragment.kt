package com.example.mltradix.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mltradix.R
import com.example.mltradix.adapter.TitleAdapter

class CoinFragment : Fragment(),TitleAdapter.CallBack {


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
        return inflater.inflate(R.layout.fragment_coin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var mutableList: MutableList<String>
    private lateinit var titleAdapter: TitleAdapter
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
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CoinFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClickMess(int: Int, messModel: String) {


//        titleAdapter.index = int
//        titleAdapter.notifyDataSetChanged()

        titleAdapter =
            TitleAdapter(mutableList, this, int, 1)
        var staggeredGridLayoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.adapter = titleAdapter
    }
}