package com.example.mltradix.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mltradix.R


class SignUpFragment : Fragment() {

    val CHECK_SIGN_UP: String = "check sign up"
    val EMAIL: String = "email"
    val PASS: String = "pass"

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
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        setAction()
    }

    private fun setAction() {
        login.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        forgotPass.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, ConfirmEmailFragment.newInstance())
                ?.addToBackStack("confirm email")
                ?.commit()
        }

        buttonSingUp.setOnClickListener {
            if (checkInputEmpty()) {
                saveIndex(1)
                saveEmail(editTextEmail.text.toString())
                savePass(editTextPass.text.toString())
                Toast.makeText(context, "Sign up success! ", Toast.LENGTH_SHORT).show()
                activity?.supportFragmentManager?.popBackStack()
            } else {
                Toast.makeText(context, "Sign up failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    lateinit var editTextEmail: EditText
    lateinit var editTextPass: EditText
    lateinit var login: TextView
    lateinit var forgotPass: TextView
    lateinit var buttonSingUp: Button

    private fun initView(view: View) {
        editTextEmail = view.findViewById(R.id.edit_text_email)
        editTextPass = view.findViewById(R.id.edit_text_pass)
        forgotPass = view.findViewById(R.id.forgot_pass)
        login = view.findViewById(R.id.login)
        buttonSingUp = view.findViewById(R.id.button_sign_up)

    }

    fun saveIndex(index: Int) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt(CHECK_SIGN_UP, index)
            apply()
        }
    }

    fun saveEmail(email: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(EMAIL, email)
            apply()
        }
    }

    fun savePass(pass: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(PASS, pass)
            apply()
        }
    }

    private fun checkInputEmpty(): Boolean {
        return (editTextEmail.text.toString() != "" || editTextPass.text.toString()!= "")
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SignUpFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}