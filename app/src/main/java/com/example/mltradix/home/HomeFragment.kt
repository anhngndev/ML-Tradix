package com.example.mltradix.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mltradix.R
import com.example.mltradix.TitleSecondModel
import com.example.mltradix.adapter.TitleAdapter
import com.example.mltradix.adapter.TitleSecondAdapter


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
        setAction()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var mutableList: MutableList<String>
    private lateinit var titleAdapter: TitleAdapter

    private lateinit var secondRecyclerView: RecyclerView
    private lateinit var secondMutableList: MutableList<TitleSecondModel>
    private lateinit var secondTitleAdapter: TitleSecondAdapter

    lateinit var loadMore: TextView
    lateinit var progressBar: ProgressBar
    var loadding: Boolean = false


    private fun setAction() {

        secondRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                Log.e("Scroll", "$dx - $dy")
                if (dy > 0 && !loadding){
                    loadMore.visibility = View.VISIBLE
                }
                if (dy < 0){
                    loadMore.visibility = View.GONE
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        loadMore.setOnClickListener {
            var oldSize = secondMutableList.size
            loadding = true
            loadMore.visibility = View.GONE
            progressBar.visibility = View.VISIBLE

            Handler().postDelayed({
                getData(secondMutableList)
                secondTitleAdapter.notifyDataSetChanged()
                secondRecyclerView.scrollToPosition(oldSize)
                Toast.makeText(context, oldSize.toString() + "/" + secondMutableList.size, Toast.LENGTH_SHORT).show()

                progressBar.visibility = View.GONE
                loadding = false
            },3000 )
        }

//        SET_TOUCH_HELPER
        var itemTouchHelper: ItemTouchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.DOWN or ItemTouchHelper.UP,
                ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    secondTitleAdapter.notifyItemMoved(
                        viewHolder.layoutPosition,
                        target.layoutPosition
                    )
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    var positionOfData = viewHolder.layoutPosition
                    when (direction) {
                        ItemTouchHelper.LEFT -> {
                            secondMutableList.removeAt(positionOfData)
                            secondTitleAdapter.notifyItemRemoved(viewHolder.layoutPosition)
                        }
                        ItemTouchHelper.RIGHT -> {
                            secondMutableList.removeAt(positionOfData)
                            secondTitleAdapter.notifyItemRemoved(viewHolder.layoutPosition)
                        }
                    }
                }
            })

        itemTouchHelper.attachToRecyclerView(secondRecyclerView)
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_1)
        loadMore = view.findViewById(R.id.load_more)
        progressBar = view.findViewById(R.id.progress_bar)

//        FIRST_TITLE_ADAPTER
        mutableList = mutableListOf()
        mutableList.add("INDEX")
        mutableList.add("SHARES")
        mutableList.add("CURRENCIES")
        mutableList.add("FUTURES")
        mutableList.add("CRYPTO")
        titleAdapter =
            TitleAdapter(mutableList, this, 0, 1)
        var staggeredGridLayoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.adapter = titleAdapter


//       SECOND_TITLE_ADAPTER
        secondRecyclerView = view.findViewById(R.id.recycler_view_2)
        secondMutableList = mutableListOf()
        getData(secondMutableList)
        var staggeredGridLayoutManager2 = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)
        secondTitleAdapter =
            TitleSecondAdapter(secondMutableList)
        secondRecyclerView.layoutManager = staggeredGridLayoutManager2
        secondRecyclerView.adapter = secondTitleAdapter
        secondRecyclerView.setHasFixedSize(true)

    }

    fun getData(list: MutableList<TitleSecondModel>) {
        var item1 = TitleSecondModel("DOWN JONES", "NYSE", "10:44:45", "20.047,50", "+203 (+1,04%)")
        var item2 = TitleSecondModel("FTSE 100", "LONDON", "10:44:45", "20.047,50", "+203 (+1,04%)")
        var item3 = TitleSecondModel("IBEX 35", "MADRID", "10:44:45", "20.047,50", "+203 (+1,04%)")
        var item4 = TitleSecondModel("DAX", "XETRA", "10:44:45", "20.047,50", "+203 (+1,04%)")
        list.add(item1)
        list.add(item2)
        list.add(item3)
        list.add(item4)
        list.add(item4)
        list.add(item1)
        list.add(item2)
        list.add(item3)
        list.add(item4)
        list.add(item4)
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
        titleAdapter =
            TitleAdapter(mutableList, this, int, 1)
        var staggeredGridLayoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.adapter = titleAdapter
    }
}