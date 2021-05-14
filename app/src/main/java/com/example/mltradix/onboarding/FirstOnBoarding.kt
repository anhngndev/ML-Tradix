package com.example.mltradix.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mltradix.R


class FirstOnBoarding : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        return inflater.inflate(R.layout.fragment_first_on_boarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    lateinit var next:TextView

    private fun initView(view: View) {
//        next = view.findViewById(R.id.first)
//        next.setOnClickListener {
//
//        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FirstOnBoarding().apply {
                arguments = Bundle().apply {

                }
            }
    }

    interface Callback {
        fun next()
    }
}