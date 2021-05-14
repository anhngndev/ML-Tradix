package com.example.mltradix.login

import android.content.Context
import android.content.Intent
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
import com.example.mltradix.home.HomeActivity


class LoginFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        setAction()
    }

    override fun onResume() {
        super.onResume()

        fillAccountInfor()
    }

    private fun setAction() {
        signUp.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, SignUpFragment.newInstance())?.addToBackStack("sign up")
                ?.commit()
       }

        forgotPass.setOnClickListener {

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, ConfirmEmailFragment.newInstance())
                ?.addToBackStack("confirm email")
                ?.commit()
        }

        login.setOnClickListener {
            if (validateAccount()){
//                LOGIN
                val intent = Intent(activity, HomeActivity::class.java)
                startActivity(intent)
            } else{
                Toast.makeText(context, "Validated fail! ", Toast.LENGTH_SHORT).show()

            }
        }

        fillAccountInfor()
    }

    var indexFragment: Int = 0
    lateinit var editTextUserEmail: EditText
    lateinit var editTextPass: EditText
    lateinit var signUp: TextView
    lateinit var forgotPass: TextView
    lateinit var login: Button

    private fun initView(view: View) {
        editTextUserEmail = view.findViewById(R.id.edit_text_email)
        editTextPass = view.findViewById(R.id.edit_text_pass)
        forgotPass = view.findViewById(R.id.forgot_pass)
        signUp = view.findViewById(R.id.sign_up_text)
        login = view.findViewById(R.id.button_login)

    }

    private fun validateAccount(): Boolean {
        return (editTextUserEmail.text.toString() == getEmail() && editTextPass.text.toString() == getPass())
    }

    fun fillAccountInfor(){
        if (getIndex() == 0) {
            Toast.makeText(context, "Please sign up", Toast.LENGTH_SHORT).show()
        } else {
            editTextUserEmail.setText(getEmail())
            editTextPass.setText(getPass())
        }
    }

    fun getIndex(): Int {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return 0
        return sharedPref.getInt(CHECK_SIGN_UP, 0)
    }

    fun getEmail(): String {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return ""
        return sharedPref.getString(EMAIL, "").toString()
    }

    fun getPass(): String {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return ""
        return sharedPref.getString(PASS, "").toString()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}