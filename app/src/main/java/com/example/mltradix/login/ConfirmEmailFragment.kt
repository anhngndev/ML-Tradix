package com.example.mltradix.login

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mltradix.R


class ConfirmEmailFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_confirm_email, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        setAction()
        handler.post(runnableTime)
    }

    private fun setAction() {
        runnableTime = Runnable {
            currentTime--
            if (currentTime > 0) {
                timmer.text = "Wait " + currentTime + " seconds before sending it"
                handler.postDelayed(runnableTime, 100)
            }
            if (currentTime == 0) {

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, ConfirmPassFragment.newInstance())
                    ?.addToBackStack("confirm pass")
                    ?.commit()
            }
        }
        resend.setOnClickListener {
            if (currentTime == 0) {
//                currentTime = 30
//                handler.postDelayed(runnableTime, 1000)
            } else {
                Toast.makeText(context, "wait", Toast.LENGTH_SHORT).show()
            }
        }
    }

    var currentTime: Int = 30
    lateinit var timmer: TextView
    lateinit var resend: Button
    var handler = Handler()
    lateinit var runnableTime: Runnable

    private fun initView(view: View) {
        resend = view.findViewById(R.id.button_resend)
        timmer = view.findViewById(R.id.timmer)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ConfirmEmailFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}