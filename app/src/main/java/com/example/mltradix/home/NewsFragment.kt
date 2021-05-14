package com.example.mltradix.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mltradix.NewsModel
import com.example.mltradix.R
import com.example.mltradix.adapter.NewsAdapter
import com.example.mltradix.adapter.TitleAdapter

class NewsFragment : Fragment(), TitleAdapter.CallBack, NewsAdapter.CallBack {


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
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)

    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var mutableList: MutableList<String>
    private lateinit var titleAdapter: TitleAdapter

    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var newsMutableList: MutableList<NewsModel>
    private lateinit var newsAdapter: NewsAdapter

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_1)
        mutableList = mutableListOf()
        mutableList.add("EDITORIAL")
        mutableList.add("CRYPTO NEWS")
        mutableList.add("RAW MATERIAL")
        mutableList.add("ECONOMICS")
        titleAdapter =
            TitleAdapter(mutableList, this,0,2)
        var staggeredGridLayoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.adapter = titleAdapter

        newsRecyclerView = view.findViewById(R.id.recycler_view_2)
        newsMutableList = mutableListOf()

        var news1 = NewsModel(
            R.drawable.image_2390177, "ATLANTIA", "ALT -3,87%",
            "Illum velit nam voluptatum enim aut, ratione ratione officiis totam., Mollitia eum sint tempora ducimus",
            "3 Sept 2020"
        )
        var news2 = NewsModel(
            R.drawable.image_2351087, "XIAOMI", "HKD -2,13%",
            "Illum velit nam voluptatum enim aut, ratione ratione officiis totam., Mollitia eum sint tempora ducimus",
            "2 Sept 2020"
        )
        var news3 = NewsModel(
            R.drawable.image_2390177, "APPLE", "AAPL -0,91%",
            "Illum velit nam voluptatum enim aut, ratione ratione officiis totam., Mollitia eum sint tempora ducimus",
            "1 Sept 2020"
        )
        newsMutableList.add(news1)
        newsMutableList.add(news2)
        newsMutableList.add(news3)
        newsMutableList.add(news1)

        newsAdapter =
            NewsAdapter(newsMutableList, this)
        var staggeredGridLayoutManager2 = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)
        newsRecyclerView.layoutManager = staggeredGridLayoutManager2
        newsRecyclerView.adapter = newsAdapter

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            NewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClickMess(int: Int, messModel: String) {
        titleAdapter =
            TitleAdapter(mutableList, this, int,2)
        var staggeredGridLayoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.adapter = titleAdapter
    }

    override fun onClickMess(int: Int, messModel: NewsModel) {

        val args = Bundle()
        // Send string data as key value format
        args.putString("title",messModel.name)
        val fragment = DetailNewsFragment()
        fragment.arguments = args

        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment, "detail new")
            ?.addToBackStack("detail new")?.commit()


    }
}