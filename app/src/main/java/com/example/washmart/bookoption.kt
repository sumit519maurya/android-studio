package com.example.washmart

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class bookoption : AppCompatActivity() {
    private lateinit var shirtqt: TextView
    private lateinit var tshirtqt: TextView
    private lateinit var pantqt: TextView
    private lateinit var shortqt: TextView
    private var count=0
    private var count2=0
    private var count3=0
    private var count4=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bookoption)
        val back:Button=findViewById(R.id.backbtn)
        val next:Button=findViewById(R.id.nextbtn)
        val shirt:CheckBox=findViewById(R.id.shirt)
        val tshirt:CheckBox=findViewById(R.id.tshirt)
        val pants:CheckBox=findViewById(R.id.pants)
        val shorts:CheckBox=findViewById(R.id.shorts)
        val total:Button=findViewById(R.id.total)
        val totaltext:TextView=findViewById(R.id.totaltext)
         shirtqt=findViewById(R.id.shirtqt)
         tshirtqt=findViewById(R.id.tshirtqt)
         pantqt=findViewById(R.id.paintqt)
         shortqt=findViewById(R.id.shortqt)


       back.setOnClickListener {
           val i= Intent(this,homepage::class.java)
           startActivity(i)
       }
        next.setOnClickListener {
            val i= Intent(this,orderpage2::class.java)
            startActivity(i)
        }
        total.setOnClickListener {
            var total1 = 0
            var isValid = true

            if (shirt.isChecked) {
                val quantity = shirtqt.text.toString().toIntOrNull()
                if (quantity != null && quantity > 0){
                total1 += 10 * quantity
            }else{
                isValid=false
                }
            }
            if (tshirt.isChecked) {
                val quantity = tshirtqt.text.toString().toIntOrNull()
                if (quantity != null && quantity > 0){
                    total1 += 20 * quantity
                }else{
                    isValid=false
                }
            }
            if (pants.isChecked) {
                val quantity = pantqt.text.toString().toIntOrNull()
                if (quantity != null && quantity > 0){
                    total1 += 25 * quantity
                }else{
                    isValid=false
                }
            }
            if (shorts.isChecked) {
                val quantity = shortqt.text.toString().toIntOrNull()
                if (quantity != null && quantity > 0){
                    total1 += 15 * quantity
                }else{
                    isValid=false
                }
            }
            if (isValid){
                totaltext.text="Total:-$total1"
            }else{
                Toast.makeText(this,"Please Enter The Quantity",Toast.LENGTH_LONG).show()
            }
        }
    }
    fun add(v: View) {
        count++
        shirtqt.text=count.toString()
    }
    fun minus(v: View) {
        if (count <= 0) count = 0 else count--
        shirtqt.text = count.toString()
    }
    fun add2(v: View) {
        count2++
        tshirtqt.text=count2.toString()
    }
    fun minus2(v: View) {
        if (count2 <= 0) count2= 0 else count2--
        tshirtqt.text = count2.toString()
    }
    fun add3(v: View) {
        count3++
        pantqt.text=count3.toString()
    }
    fun minus3(v: View) {
        if (count3 <= 0) count3 = 0 else count3--
        pantqt.text = count3.toString()
    }
    fun add4(v: View) {
        count4++
        shortqt.text=count4.toString()
    }
    fun minus4(v: View) {
        if (count4 <= 0) count4 = 0 else count4--
        shortqt.text = count4.toString()
    }
}