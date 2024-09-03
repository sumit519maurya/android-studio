package com.example.washmart

import BillAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class userdesign : AppCompatActivity() {

    private lateinit var dbHelper: Dbhelper
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_userdesign)

        dbHelper = Dbhelper(this)
        val username = intent.getStringExtra("username") ?: "N/A"
        val user = dbHelper.getUser(username)
        val usname:TextView=findViewById(R.id.usname)
        val uspass:TextView=findViewById(R.id.uspass)
        val usphone:TextView=findViewById(R.id.usphone)
        val usemail:TextView=findViewById(R.id.usemail)
        val lv:ListView=findViewById(R.id.lv)

        if (user != null) {
            usname.text = user.username
            usemail.text = user.email
            usphone.text = user.phoneNumber
            uspass.text = user.password
        } else {
            usname.text = "N/A"
            usemail.text = "N/A"
            usphone.text = "N/A"
            uspass.text = "N/A"
        }

        val sharedPref = this.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)

        // Fetch the bills for the logged-in user
        val dbHelper = Dbhelper(this)
        val userid =sharedPref.getLong("userid",1L)
        val bills = dbHelper.getBillsForUser(userid)

        // Set up the adapter for the ListView
        val adapter = BillAdapter(this, bills)
        lv.adapter = adapter

    }
}