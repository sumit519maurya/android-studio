package com.example.washmart

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class signin : AppCompatActivity() {

    private lateinit var dbHelper: Dbhelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)

        dbHelper = Dbhelper(this)
        val signinbtn=findViewById<Button>(R.id.signinbtn)
        val signup =findViewById<TextView>(R.id.signup)
        val namesi =findViewById<TextView>(R.id.namesi)
        val emailsi =findViewById<TextView>(R.id.emailsi)
        val phonesi =findViewById<TextView>(R.id.phonesi)
        val passsi =findViewById<TextView>(R.id.passsi)
        val logo = findViewById<ImageView>(R.id.logo)


        startFlipAnimation(logo)


        signinbtn.setOnClickListener {

            val username = namesi.text.toString()
            val password = passsi.text.toString()
            val email = emailsi.text.toString()
            val phoneNumber = phonesi.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty() && phoneNumber.isNotEmpty()) {
                if (dbHelper.addUser(username, password, email, phoneNumber)) {
                   /* val sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("username", username)
                        putString("password", password)
                        putString("email", email)
                        putString("phoneNumber", phoneNumber)
                        apply()
                    }*/
                    Log.d("signin", "Storing data: username=$username, password=$password, email=$email, phoneNumber=$phoneNumber")

                    Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, login::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
        signup.setOnClickListener {
            val i=Intent(this,login::class.java)
            startActivity(i)
        }
    }
    private fun startFlipAnimation(view: ImageView) {
        // Flip animation
        val flipAnimator = ObjectAnimator.ofFloat(view, "rotationY", 0f, 360f).apply {
            duration = 2000 // 1 second for full flip
            repeatCount = ObjectAnimator.INFINITE // Repeat animation infinitely
            repeatMode = ObjectAnimator.RESTART // Restart animation after each repetition
        }

        // Start the animation
        flipAnimator.start()
    }
}