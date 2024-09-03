package com.example.washmart

import BillAdapter
import android.content.Context
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class history : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val sharedPref = this.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val listView: ListView = findViewById(R.id.lv)

        // Fetch the bills for the logged-in user
        val dbHelper = Dbhelper(this)
        val userid =sharedPref.getLong("userid",1L)
        val bills = dbHelper.getBillsForUser(userid)

        // Set up the adapter for the ListView
        val adapter = BillAdapter(this, bills)
        listView.adapter = adapter
    }
}