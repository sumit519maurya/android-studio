package com.example.washmart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class login : Fragment() {

    private lateinit var dbHelper: Dbhelper

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

        signup.setOnClickListener {
            val i=Intent(requireContext(),signin::class.java)
            startActivity(i)
        }
        login.setOnClickListener {
            val i=Intent(requireContext(),homepage::class.java)
            val username = namelg.text.toString()
            val password = passlg.text.toString()
            if (dbHelper.checkUser(username, password)) {
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(i)
            } else {
                Toast.makeText(requireContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }
        admin.setOnClickListener{
            val i=Intent(requireContext(),adminpage::class.java)
            startActivity(i)
        }
        return view
    }

}
