package com.example.washmart

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
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

        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_password, null)
        val passwordInput = dialogView.findViewById<EditText>(R.id.password_input)

        builder.setView(dialogView)

        builder.setPositiveButton("Submit") { dialog, which ->
            val enteredPassword = passwordInput.text.toString()
            if (enteredPassword == FIXED_PASSWORD) {
                val intent = Intent(requireContext(), adminpage::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Incorrect password", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        builder.show()
    }

}
