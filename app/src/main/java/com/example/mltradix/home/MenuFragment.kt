package com.example.mltradix.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mltradix.MenusModel
import com.example.mltradix.R
import com.example.mltradix.adapter.MenusAdapter
import com.example.mltradix.adapter.MenusAdapterTest
import com.example.mltradix.adapter.TitleAdapter


class MenuFragment : Fragment(), MenusAdapter.CallBack {


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
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
    }

    private lateinit var recyclerViewBase: RecyclerView
    private lateinit var mutableListBase: MutableList<MenusModel>
    private lateinit var menusAdapterBase: MenusAdapter
    private lateinit var menusAdapterTest: MenusAdapterTest

    private lateinit var recyclerViewTools: RecyclerView
    private lateinit var mutableListTools: MutableList<MenusModel>
    private lateinit var menusAdapterTools: MenusAdapter

    private lateinit var recyclerViewMarkets: RecyclerView
    private lateinit var mutableListMarkets: MutableList<MenusModel>
    private lateinit var menusAdapterMarkets: MenusAdapter

    private fun initView(view: View) {
        var staggeredGridLayoutManager = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)

        recyclerViewBase = view.findViewById(R.id.recycler_view_1)
        mutableListBase = mutableListOf()
        mutableListBase.add(MenusModel(R.drawable.icons_8_alarm, "Alerts"))
        mutableListBase.add(MenusModel(R.drawable.icons_8_left_and_right_arrows, "Predictions"))
        mutableListBase.add(MenusModel(R.drawable.icons_8_pin, "Saved elements"))
        mutableListBase.add(MenusModel(R.drawable.icons_8_no_entry, "Remove Ads"))

        mutableListTools = mutableListOf()
        mutableListTools.add(MenusModel(R.drawable.icons_8_profit_2, "Select Stocks"))
        mutableListTools.add(MenusModel(R.drawable.icons_8_swap, "Currency Exchange"))
        mutableListTools.add(MenusModel(R.drawable.icons_8_video_call, "Webinar"))
        mutableListTools.add(MenusModel(R.drawable.icons_8_rent, "Best Broker"))
//
        mutableListMarkets = mutableListOf()
        mutableListMarkets.add(MenusModel(R.drawable.icons_8_profit_2, "Select Stocks"))
//
        menusAdapterTest = MenusAdapterTest(mutableListBase, "Tools",mutableListTools, "Market", mutableListMarkets)
        recyclerViewBase.layoutManager = staggeredGridLayoutManager
        recyclerViewBase.adapter = menusAdapterTest
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MenuFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onClickMenuItem(int: Int, messModel: MenusModel) {
        TODO("Not yet implemented")
    }
}