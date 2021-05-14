package com.example.mltradix.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.mltradix.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
        setAction()
    }

    private fun setAction() {

        bottomNavigationView.setOnNavigationItemSelectedListener {

            bottomNavigationView.itemIconTintList =
                ContextCompat.getColorStateList(this, R.color.item_bottom_navigation_color)
            bottomNavigationView.itemBackground =
                ContextCompat.getDrawable(this, R.drawable.item_bottom_nav_background)

            when (it.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, HomeFragment.newInstance(), "home")
                        .addToBackStack("home").commit()
                }
                R.id.coin -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, CoinFragment.newInstance(), "coin")
                        .addToBackStack("coin").commit()
                }
                R.id.news -> {
                    if (supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name != "detail new")
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, NewsFragment.newInstance(), "news")
                            .addToBackStack("news").commit()
                }
                R.id.menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MenuFragment.newInstance(), "menu")
                        .addToBackStack("menu").commit()
                }
            }
            true
        }
        bottomNavigationView.selectedItemId = R.id.home
    }

    private fun initView() {
        bottomNavigationView = findViewById(R.id.navigation)
    }

    override fun onBackPressed() {
        when (supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name) {
            "detail new" -> {
                supportFragmentManager.popBackStack()
            }
            "home" -> {
                val builder =
                    AlertDialog.Builder(this@HomeActivity)
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
            else -> {
                super.onBackPressed()
            }
        }

        when (supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name) {
            "detail new" -> {
                bottomNavigationView.selectedItemId = R.id.news

            }
            "news" -> {
                bottomNavigationView.selectedItemId = R.id.news
            }
            "home" -> {
                bottomNavigationView.selectedItemId = R.id.home

            }
            "coin" -> {
                bottomNavigationView.selectedItemId = R.id.coin

            }
            "menu" -> {
                bottomNavigationView.selectedItemId = R.id.menu
            }
        }
    }

    lateinit var bottomNavigationView: BottomNavigationView

}