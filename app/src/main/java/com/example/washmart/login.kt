package com.example.washmart

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class login : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.login, container, false)

        val signup:TextView=view.findViewById(R.id.signup)
        val login:Button=view.findViewById(R.id.login)
        val passlg:EditText=view.findViewById(R.id.passlg)
        val namelg:EditText=view.findViewById(R.id.namelg)

        signup.setOnClickListener {
            val i=Intent(requireContext(),signin::class.java)
            startActivity(i)
        }
        login.setOnClickListener {
            val i=Intent(requireContext(),homepage::class.java)
            startActivity(i)
        }
        return view
    }

}
