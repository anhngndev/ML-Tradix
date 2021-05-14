package com.example.mltradix.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mltradix.R


class ConfirmPassFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_confirm_pass, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        setAction()
    }
    lateinit var login : Button

    private fun setAction() {
        login.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, LoginFragment.newInstance())
                ?.addToBackStack("login")
                ?.commit()
        }

    }

    private fun initView(view: View) {
        login = view.findViewById(R.id.button_login)

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ConfirmPassFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}