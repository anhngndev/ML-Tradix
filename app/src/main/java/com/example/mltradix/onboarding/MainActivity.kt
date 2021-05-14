package com.example.mltradix.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mltradix.R
import com.example.mltradix.login.LoginActivity

class MainActivity : AppCompatActivity() {
    val CHECK_SKIP_ON_BOARDING: String = "index on boarding"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setAction()
//        saveStatus(0)
    }

    override fun onResume() {
        super.onResume()
        if(getStatus() ==1 ) finish()
    }

    override fun onBackPressed() {
        if (currentOnBoarding > 0) currentOnBoarding--;
        if (currentOnBoarding == 0) {
//            show dialog
            val builder =
                AlertDialog.Builder(this@MainActivity)
            builder.setTitle("TRADIX")
            builder.setMessage("Do you want to exit?")
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
        when (currentOnBoarding) {
            0 -> {
                back.visibility = View.INVISIBLE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, FirstOnBoarding.newInstance())
                    .addToBackStack(null).commit()
            }
            1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SecondOnBoarding.newInstance())
                    .addToBackStack(null).commit()
            }
        }
    }

    private fun setAction() {
        skip.setOnClickListener {
            saveStatus(1)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        back.setOnClickListener {
            currentOnBoarding--;
            when (currentOnBoarding) {
                0 -> {
                    back.visibility = View.INVISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, FirstOnBoarding.newInstance())
                        .addToBackStack(null).commit()
                }
                1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SecondOnBoarding.newInstance())
                        .addToBackStack(null).commit()
                }
            }
        }

        next.setOnClickListener {
            currentOnBoarding++;
            when (currentOnBoarding) {
                1 -> {
                    back.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SecondOnBoarding.newInstance())
                        .addToBackStack(null).commit()
                }
                2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ThirdOnBoarding.newInstance()).addToBackStack(null)
                        .commit()
                }
                3 -> {
                    saveStatus(1)
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }

            }
        }
    }

    private fun initView() {

        skip = findViewById(R.id.skip)
        back = findViewById(R.id.back)
        next = findViewById(R.id.first)

        back.visibility = View.INVISIBLE
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FirstOnBoarding.newInstance()).addToBackStack(null)
            .commit()

        when (getStatus()) {
            1 -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    var currentOnBoarding: Int = 0

    lateinit var skip: TextView
    lateinit var next: TextView
    lateinit var back: ImageView

    fun saveStatus(index: Int) {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt(CHECK_SKIP_ON_BOARDING, index)
            apply()
        }
    }

    fun getStatus(): Int {
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return 0
        return sharedPref.getInt(CHECK_SKIP_ON_BOARDING, 0)
    }
}