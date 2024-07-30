package com.example.washmart

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class bookoption : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bookoption)
        val back:Button=findViewById(R.id.backbtn)
        val next:Button=findViewById(R.id.nextbtn)
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
         navigation.setNavigationItemSelectedListener { item ->
             val id = item.itemId
             if (id == R.id.home) {
                 Toast.makeText(this, "sdsfsf", Toast.LENGTH_LONG).show()

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
       back.setOnClickListener {
           val i= Intent(this,homepage::class.java)
           startActivity(i)
       }
        next.setOnClickListener {
            val i= Intent(this,orderpage2::class.java)
            startActivity(i)
        }
    }
}