package com.example.mltradix.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.mltradix.R
import com.example.mltradix.onboarding.FirstOnBoarding

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LoginFragment.newInstance()).addToBackStack("login")
            .commit()
    }

    override fun onBackPressed() {
        when (supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name) {
            "login" -> {
                val builder =
                    AlertDialog.Builder(this@LoginActivity)
                builder.setTitle("TRADIX")
                builder.setMessage("Do you want to log out?")
                    .setCancelable(false)
                    .setPositiveButton(
                        "Yes"
                    ) { dialog, id -> finish() }
                    .setNegativeButton(
                        "No"
                    ) { dialog, id -> dialog.cancel() }
                val alert = builder.create()
                alert.show()
            }
            "sign up" -> {
                super.onBackPressed()
            }
            "confirm pass" -> {
                supportFragmentManager.popBackStack()
                supportFragmentManager.popBackStack()
            }
            "confirm email" -> {
                super.onBackPressed()
            }
        }
    }
}