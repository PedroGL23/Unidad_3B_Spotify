package com.example.unidad_3b_spotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    goToFragment(DummieFragment())
                    true
                }
                R.id.action_search -> {
                    goToFragment(PlaylistFragment())
                    true
                }
                R.id.action_library -> {
                    goToFragment(DummieFragment())
                    true
                }
                R.id.action_premium -> {
                    goToFragment(DummieFragment())
                    true
                }
                else -> false
            }
        }
        //  Con esta linea se indica el fragment desde el que debe partir.
        bottomNavigationView.selectedItemId = R.id.action_search

    }
    fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit()

    }
}