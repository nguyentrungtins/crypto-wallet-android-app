package com.example.hien_android_final

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hien_android_final.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.watasolutions.w3_databinding_wm.MainViewModel

class Home : AppCompatActivity() {
    // Bottom Navigation
    private lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: MainViewModel
    // Button Add new Event animation
    private var rotateOpen: Animation? = null  // Button Add new Event animation
    private var rotateClose: Animation? = null  // Button Add new Event animation
    private var fromBottom: Animation? = null // Button Add new Event animation
    private var toBottom: Animation? = null

    var btn_add: FloatingActionButton? = null
    var btn_new_clock:FloatingActionButton? = null
    var btn_new_calendar:FloatingActionButton? = null
    var btn_new_event:FloatingActionButton? = null
    private var clicked: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        init()
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.Theme_Dark)
        } else {
            setTheme(R.style.Theme_Light)
        }
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        if(!viewModel.checkLogin()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding = ActivityHomeBinding.inflate(layoutInflater)

//        listenerErrorEvent()
        setContentView(R.layout.activity_home)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListerner)
        supportFragmentManager.beginTransaction().replace(R.id.flFragment, CoinsFragment())
            .commit()

        // Function Button
        val btn_add = findViewById<FloatingActionButton>(R.id.btn_add)
        val btn_new_clock = findViewById<FloatingActionButton>(R.id.btn_new_clock)
        val btn_new_calendar = findViewById<FloatingActionButton>(R.id.btn_new_calendar)
        val btn_new_event = findViewById<FloatingActionButton>(R.id.btn_new_event)

        btn_add.setOnClickListener {
            clicked = !clicked!!

            if (!clicked!!) {
                btn_new_clock.startAnimation(fromBottom)
                btn_new_calendar.startAnimation(fromBottom)
                btn_new_event.startAnimation(fromBottom)
                btn_add.startAnimation(rotateOpen)
            } else {
                btn_new_clock.startAnimation(toBottom)
                btn_new_calendar.startAnimation(toBottom)
                btn_new_event.startAnimation(toBottom)
                btn_add.startAnimation(rotateClose)
            }
        }

    }
    private fun init() {
        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rote_open_anim)
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rote_close_anim)
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim)
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim)
        clicked = true
    }

    private fun listenerErrorEvent() {
        viewModel.isErrorEvent.observe(this) { errMsg ->
            Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show()
        }
    }
    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flFragment,fragment)
        fragmentTransaction.commit()

    }
    private val navListerner =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFrament: Fragment? = null
            when (item.itemId) {
                R.id.coin -> selectedFrament = CoinsFragment()
                R.id.gainer -> selectedFrament = GainersFragment()
                R.id.loser -> selectedFrament = LosersFragment()
                R.id.setting -> selectedFrament = SettingFragment()
            }
            supportFragmentManager.beginTransaction().replace(R.id.flFragment, selectedFrament!!)
                .commit()
            true
        }

}