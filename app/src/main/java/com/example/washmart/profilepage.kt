package com.example.washmart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class profilepage : AppCompatActivity() {

    private lateinit var galleryButton: Button
    private lateinit var imageView: ImageView

    companion object {
        private const val IMAGE_REQUEST_CODE = 100
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
        Log.d("ProfilePage", "Retrieved data: username=$username, email=$email, phoneNumber=$phoneNumber, password=$password")

        galleryButton = findViewById(R.id.gallery)
        imageView = findViewById(R.id.image_view)
        val usname: TextView = findViewById(R.id.usname)
        val logoutButton: Button = findViewById(R.id.logout)
        val uspass: TextView = findViewById(R.id.uspass)
        val usphone: TextView = findViewById(R.id.usphone)
        val usemail: TextView = findViewById(R.id.usemail)

        usname.text = username
        usemail.text = email
        usphone.text = phoneNumber
        uspass.text = password

        logoutButton.setOnClickListener {
            // Create an alert dialog
            val builder = AlertDialog.Builder(this)

            // Set the title, message, and button options for the dialog
            builder.setTitle("Logout")
            builder.setMessage("Warning: Do you want to logout?")

            // Set "Yes" button behavior
            builder.setPositiveButton("Yes") { dialog, _ ->
                // Start the login activity
                val intent = Intent(this, MainActivity::class.java) // Adjust the activity to your LoginActivity or equivalent
                // Clear the activity stack so that the user cannot navigate back to this activity
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

                // Close the current activity
                finish()

                // Optional: If you want to exit the app completely
                // This line may not be necessary in most cases, as finish() should be sufficient
                // System.exit(0)

                dialog.dismiss() // Close the dialog
            }

            // Set "No" button behavior to just close the dialog
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss() // Close the dialog
            }

            // Show the alert dialog
            builder.create().show()
        }

        galleryButton.setOnClickListener {
            pickImageGallery()
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            imageView.setImageURI(data?.data)
        }
    }
}
