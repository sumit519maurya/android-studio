package com.example.washmart

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class adminpage : AppCompatActivity() {
    private lateinit var dbHelper: Dbhelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_adminpage)

        dbHelper = Dbhelper(this)
        val lv:ListView=findViewById(R.id.lv)

        val users = dbHelper.getAllUsers()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
        lv.adapter = adapter

        lv.setOnItemClickListener { _, _, position, _ ->
            val selectedUser = users[position]
            // You can use an Intent to start a new Activity or Fragment to show user details
            // For simplicity, we'll just show a Toast message
             Toast.makeText(this, "Selected User: $selectedUser", Toast.LENGTH_SHORT).show()
        }
    }
}