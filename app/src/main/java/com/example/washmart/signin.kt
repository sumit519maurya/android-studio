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
import java.util.regex.Pattern

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

            var isValid = true

            if (username.isEmpty()) {
                namesi.error = "Username cannot be empty"
                isValid = false
            } else {
                namesi.error = null
            }

            if (email.isEmpty() || !validateEmail(email)) {
                emailsi.error = if (email.isEmpty()) "Email cannot be empty" else "Invalid email format"
                isValid = false
            } else {
                emailsi.error = null
            }

            if (phoneNumber.isEmpty() || !validatePhone(phoneNumber)) {
                phonesi.error = if (phoneNumber.isEmpty()) "Phone number cannot be empty" else "Phone number must be exactly 10 digits"
                isValid = false
            } else {
                phonesi.error = null
            }

            if (password.isEmpty() || !validatePassword(password)) {
                passsi.error = if (password.isEmpty()) "Password cannot be empty" else "Password must contain both letters and numbers & use more than 5 letters"
                isValid = false
            } else {
                passsi.error = null
            }

            if (isValid) {
                if (dbHelper.addUser(username, password, email, phoneNumber)) {
                    Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, login::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
        signup.setOnClickListener {
            val i=Intent(this,login::class.java)
            startActivity(i)
        }
    }
    private fun startFlipAnimation(view: ImageView) {
        val flipAnimator = ObjectAnimator.ofFloat(view, "rotationY", 0f, 360f).apply {
            duration = 2000 //
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
        }


        flipAnimator.start()
    }
    private fun validateEmail(email: String): Boolean {
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
        )
        return emailPattern.matcher(email).matches()
    }

    private fun validatePhone(phone: String): Boolean {
        return phone.length == 10 && phone.all { it.isDigit() }
    }

    private fun validatePassword(password: String): Boolean {
        val letterPattern = Pattern.compile(".*[a-zA-Z].*")
        val numberPattern = Pattern.compile(".*\\d.*")
        return password.length >= 5 && letterPattern.matcher(password).matches() && numberPattern.matcher(password).matches()
    }
}