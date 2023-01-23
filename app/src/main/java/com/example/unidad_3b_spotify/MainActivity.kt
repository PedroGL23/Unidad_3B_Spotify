package com.example.unidad_3b_spotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import layout.RecyclerAdapter
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavigationController(R.id.nav_host_fragment))

        val json = readJsonFromFile("playlists.json")
        val playlist = Gson().fromJson(json, PlaylistResponse::class.java)
        Log.i("MainActivity", playlist.data.toString())

        var mainRecyclerView: RecyclerView = findViewById(R.id.mainRecyclerView)

        val mAdapter = RecyclerAdapter(playlist.data)
        mainRecyclerView.layoutManager = GridLayoutManager(this, 2)
        mainRecyclerView.adapter = mAdapter



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

    override fun onSupportNavigateUp (): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController .navigateUp() || super.onSupportNavigateUp()
    }

    fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit()

    }


    private fun readJsonFromFile(fileName: String): String {
        var json = ""
        try {
            val bufferedReader = BufferedReader(
                InputStreamReader(assets.open(fileName))
            )
            val paramsBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                paramsBuilder.append(line)
                line = bufferedReader.readLine()
            }
            json = paramsBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }
}

fun FragmentActivity.findNavigationController (@IdRes host: Int): NavController {
    try {
        val navHostFragment = supportFragmentManager.findFragmentById(host) as NavHostFragment
        return navHostFragment .findNavController()
    } catch (e: Exception) {
        throw IllegalStateException( "Activity $this does not have a NavController set on $host")
    }
}