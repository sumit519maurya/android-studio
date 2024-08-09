package com.example.washmart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class profilepage : AppCompatActivity() {

    private lateinit var Gallery: Button
    private lateinit var imageView: ImageView

    companion object{
        val IMAGE_REQUEST_CODE=100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.profilepage)

        val sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "N/A")
        val email = sharedPref.getString("email", "N/A")
        val phoneNumber = sharedPref.getString("phoneNumber", "N/A")
        val password = sharedPref.getString("password", "N/A")
        Log.d("profilepage", "Retrieved data: username=$username, email=$email, phoneNumber=$phoneNumber, password=$password")

        Gallery = findViewById(R.id.gallery)
        imageView = findViewById(R.id.image_view)
        val usname:TextView=findViewById(R.id.usname)
        val uspass:TextView=findViewById(R.id.uspass)
        val usphone:TextView=findViewById(R.id.usphone)
        val usemail:TextView=findViewById(R.id.usemail)



        usname.text = username
        usemail.text = email
        usphone.text = phoneNumber
        uspass.text = password



       Gallery.setOnClickListener {
            pickImageGallery()
        }

    }

    private fun pickImageGallery() {
        val intent =Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            imageView.setImageURI(data?.data)
        }
    }
}
