package com.example.washmart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        val signinbtn=findViewById<Button>(R.id.signinbtn);
        val signup =findViewById<TextView>(R.id.signup)
        signinbtn.setOnClickListener {
            val i=Intent(this,login::class.java)
            startActivity(i)
        }
        signup.setOnClickListener {
            val i=Intent(this,login::class.java)
            startActivity(i)
        }
    }
}