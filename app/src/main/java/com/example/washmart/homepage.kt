package com.example.washmart

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class homepage : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var navigation:NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homepage)
        val booknowimgae:ImageView=findViewById(R.id.booknowimage)
        val drawerLayout: DrawerLayout = findViewById(R.id.main)
        val navigation: NavigationView = findViewById(R.id.navigation)
        val tolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(tolbar)
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            tolbar,
            R.string.opendrawer,
            R.string.closedrawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
       navigation.setNavigationItemSelectedListener { item ->
            val id = item.itemId
            if (id == R.id.home) {
                Toast.makeText(this, "rdgrg", Toast.LENGTH_LONG).show()
            } else if (id == R.id.profile) {
            }
            else if (id == R.id.order) {
            }
            else if (id == R.id.help) {
            }
            else if (id == R.id.logout) {
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        booknowimgae.setOnClickListener {
            val i=Intent(this,bookoption::class.java)
            startActivity(i)
        }


    }
}