package com.example.washmart

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class homepage : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homepage)
       val booknowimgae:ImageView=findViewById(R.id.booknowimage)
        val booknowtext:TextView=findViewById(R.id.booknowtext)

        val profileimg:ImageView=findViewById(R.id.profileimg)
        val profile:TextView=findViewById(R.id.profile)

        val drawerLayout: DrawerLayout = findViewById(R.id.main)
        val history1: RelativeLayout = findViewById(R.id.history)
        val navigation: NavigationView = findViewById(R.id.navigation)
        val tolbar: Toolbar = findViewById(R.id.toolbar)
        /*val username = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val password= intent.getStringExtra("password")*/
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
        booknowtext.setOnClickListener {
            val i=Intent(this,bookoption::class.java)
            startActivity(i)
        }
        profileimg.setOnClickListener {
            val i=Intent(this,profilepage::class.java)
            startActivity(i)
        }
        profile.setOnClickListener {
            val i=Intent(this,profilepage::class.java)
            startActivity(i)
        }

        history1.setOnClickListener {
            val i=Intent(this,history::class.java)
            startActivity(i)
        }


    }
}