package com.example.washmart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class profilepage : AppCompatActivity() {

    private lateinit var buttonOpenGallery: Button
    private lateinit var imageView: ImageView

    companion object{
        val IMAGE_REQUEST_CODE=100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.profilepage)

        buttonOpenGallery = findViewById(R.id.button_open_gallery)
        imageView = findViewById(R.id.image_view)

        buttonOpenGallery.setOnClickListener {
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
