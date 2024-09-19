package com.example.washmart

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class login : Fragment() {

    private lateinit var dbHelper: Dbhelper
    private val FIXED_PASSWORD = "sumit"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.login, container, false)
        dbHelper = Dbhelper(requireContext())
        val signup:TextView=view.findViewById(R.id.signup)
        val login:Button=view.findViewById(R.id.login)
        val passlg:EditText=view.findViewById(R.id.passlg)
        val namelg:EditText=view.findViewById(R.id.namelg)
        val admin:TextView=view.findViewById(R.id.admin)
        val logo:ImageView=view.findViewById(R.id.logo)

        startFlipAnimation(logo)

        signup.setOnClickListener {
            val i=Intent(requireContext(),signin::class.java)
            startActivity(i)
        }
        login.setOnClickListener {

            val username = namelg.text.toString()
            val password = passlg.text.toString()
            if (dbHelper.checkUser(username, password)) {
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                val sharedPref = requireContext().getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
                val user = dbHelper.getUser(username)
                with(sharedPref.edit()) {
                    putString("username", user.username)
                    putString("password", user.password)
                    putString("email", user.email)
                    putString("phoneNumber", user.phoneNumber)
                    putLong("userid", user.userid.toLong())
                    apply()
                }
                val intent = Intent(requireContext(), homepage::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
        admin.setOnClickListener {
            showPasswordDialog()
        }
        return view
    }
    private fun startFlipAnimation(view: ImageView) {
        // Flip animation
        val flipAnimator = ObjectAnimator.ofFloat(view, "rotationY", 0f, 360f).apply {
            duration = 2000 // 1 second for full flip
            repeatCount = ObjectAnimator.INFINITE // Repeat animation infinitely
            repeatMode = ObjectAnimator.RESTART // Restart animation after each repetition
        }

        flipAnimator.start()
    }
    private fun showPasswordDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Admin Access")

        // Inflate the dialog view with username and password inputs
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_passwor, null)
        val usernameInput = dialogView.findViewById<EditText>(R.id.username_input)
        val passwordInput = dialogView.findViewById<EditText>(R.id.password_input)

        builder.setView(dialogView)

        // Option to login as an existing admin
        builder.setPositiveButton("Login") { dialog, which ->
            val enteredUsername = usernameInput.text.toString()
            val enteredPassword = passwordInput.text.toString()

            // Check if the entered credentials match an admin in the database
            val dbHelper = Dbhelper(requireContext())
            if (dbHelper.isAdminValid(enteredUsername, enteredPassword)) {
                // Credentials are valid, go to the admin page
                val intent = Intent(requireContext(), adminpage::class.java)
                startActivity(intent)
            } else {
                // Show a toast if the credentials are incorrect
                Toast.makeText(requireContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Option to add a new admin
        builder.setNeutralButton("New Admin") { dialog, which ->
            showDefaultPasswordDialog()
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        builder.show()
    }
    private fun showDefaultPasswordDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Enter Default Admin Password")

        // Inflate a dialog view with only the default admin password input
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_default_password, null)
        val defaultPasswordInput = dialogView.findViewById<EditText>(R.id.default_password_input)

        builder.setView(dialogView)

        builder.setPositiveButton("Submit") { dialog, which ->
            val enteredDefaultPassword = defaultPasswordInput.text.toString()

            val DEFAULT_ADMIN_PASSWORD = "sumit"
            if (enteredDefaultPassword == DEFAULT_ADMIN_PASSWORD) {
                // Show dialog to create a new admin
                showCreateNewAdminDialog()
            } else {
                Toast.makeText(requireContext(), "Incorrect default admin password", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        builder.show()
    }

    @SuppressLint("MissingInflatedId")
    private fun showCreateNewAdminDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Create New Admin")

        // Inflate a dialog view with inputs for new admin username and password
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_create_admin, null)
        val newAdminUsernameInput = dialogView.findViewById<EditText>(R.id.new_admin_username_input)
        val newAdminPasswordInput = dialogView.findViewById<EditText>(R.id.new_admin_password_input)

        builder.setView(dialogView)

        builder.setPositiveButton("Create Admin") { dialog, which ->
            val newAdminUsername = newAdminUsernameInput.text.toString()
            val newAdminPassword = newAdminPasswordInput.text.toString()

            // Add the new admin to the database
            val dbHelper = Dbhelper(requireContext())
            val isAdded = dbHelper.addNewAdmin(newAdminUsername, newAdminPassword)

            if (isAdded) {
                Toast.makeText(requireContext(), "New admin added successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Failed to add new admin. Try again.", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        builder.show()
    }



}


