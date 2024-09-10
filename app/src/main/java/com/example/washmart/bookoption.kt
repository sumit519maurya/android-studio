package com.example.washmart

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class bookoption : AppCompatActivity() {
    private lateinit var shirtqt: TextView
    private lateinit var tshirtqt: TextView
    private lateinit var pantqt: TextView
    private lateinit var shortqt: TextView
    private lateinit var totalcloth: TextView
    private var count=0
    private var count2=0
    private var count3=0
    private var count4=0
    @SuppressLint("SuspiciousIndentation")
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
        val laundry:CheckBox=findViewById(R.id.laundry)
        val dry:CheckBox=findViewById(R.id.dry)
        val iron:CheckBox=findViewById(R.id.iron)
        val cotton:CheckBox=findViewById(R.id.cotton)
        val wool:CheckBox=findViewById(R.id.wool)
        val silk:CheckBox=findViewById(R.id.silk)
        val nylon:CheckBox=findViewById(R.id.nylon)
        val servicetext:TextView=findViewById(R.id.servicetext)
        val total:Button=findViewById(R.id.total)
        val totaltext:TextView=findViewById(R.id.totaltext)
        val ftotaltext:TextView=findViewById(R.id.ftotaltext)
        val ftotalbtn:Button=findViewById(R.id.ftotalbtn)
         shirtqt=findViewById(R.id.shirtqt)
         tshirtqt=findViewById(R.id.tshirtqt)
         pantqt=findViewById(R.id.paintqt)
         shortqt=findViewById(R.id.shortqt)
        totalcloth=findViewById(R.id.totalclothes)




       back.setOnClickListener {
           val i= Intent(this,homepage::class.java)
           startActivity(i)
       }
        next.setOnClickListener {

            val intent = Intent(this, infopage::class.java)
                .putExtra("Cloth_price", totaltext.text.toString())
                .putExtra("service_charge", servicetext.text.toString())
                .putExtra("Total_cloth", totalcloth.text.toString())
                .putExtra("ftotal", ftotaltext.text.toString())
            if (shirt.isChecked) {
                intent.putExtra("shirt", "Shirt")
            }
            if (tshirt.isChecked) {
                intent.putExtra("tshirt", "Tshirt")
            }
            if (pants.isChecked) {
                intent.putExtra("pants", "Pants")
            }
            if (shorts.isChecked) {
                intent.putExtra("shorts", "Shorts")
            }
            if (cotton.isChecked) {
                intent.putExtra("cotton", "Cotton")
            }
            if (wool.isChecked) {
                intent.putExtra("wool", "Wool")
            }
            if (silk.isChecked) {
                intent.putExtra("silk", "Silk")
            }
            if (nylon.isChecked) {
                intent.putExtra("nylon", "Nylon")
            }
            if (laundry.isChecked) {
                intent.putExtra("laundry", "Dry")
            }
            if (dry.isChecked) {
                intent.putExtra("dry", "Dry Cleaning")
            }
            if (iron.isChecked) {
                intent.putExtra("iron", "Iron")
            }
            startActivity(intent)
        }




        total.setOnClickListener {
            var total1 = 0
            var isValid = true
            val num1 = if (shirt.isChecked)shirtqt.text.toString().toInt()else 0
            val num2 =if (tshirt.isChecked) tshirtqt.text.toString().toInt()else 0
            val num3 =if (pants.isChecked) pantqt.text.toString().toInt()else 0
            val num4 =if (shorts.isChecked) shortqt.text.toString().toInt()else 0
            val sum = num1 + num2 + num3 + num4
            val result = sum

            if (shirt.isChecked) {
                totalcloth.text=result.toString()
                val quantity = shirtqt.text.toString().toIntOrNull()
                if (quantity != null && quantity > 0){
                total1 += 10 * quantity
            } else if (quantity==0){
                total1 -= 10 * quantity
                    Toast.makeText(this,"Please Enter The Quantity",Toast.LENGTH_LONG).show()
            }
                else{
                isValid=false
                }
            }
            if (tshirt.isChecked) {
                totalcloth.text=result.toString()
                val quantity = tshirtqt.text.toString().toIntOrNull()
                if (quantity != null && quantity > 0){
                    total1 += 20 * quantity
                }else if (quantity==0){
                    total1 -= 20 * quantity
                    Toast.makeText(this,"Please Enter The Quantity",Toast.LENGTH_LONG).show()
                }
                else{
                    isValid=false
                }
            }
            if (pants.isChecked) {
                totalcloth.text=result.toString()
                val quantity = pantqt.text.toString().toIntOrNull()
                if (quantity != null && quantity > 0){
                    total1 += 25 * quantity
                }else if (quantity==0){
                    total1 -= 25 * quantity
                    Toast.makeText(this,"Please Enter The Quantity",Toast.LENGTH_LONG).show()
                }
                else{
                    isValid=false
                }
            }
            if (shorts.isChecked) {
                totalcloth.text=result.toString()
                val quantity = shortqt.text.toString().toIntOrNull()
                if (quantity != null && quantity > 0){
                    total1 += 15 * quantity
                }else if (quantity==0){
                    total1 -= 15 * quantity
                    Toast.makeText(this,"Please Enter The Quantity",Toast.LENGTH_LONG).show()
                }
                else{
                    isValid=false
                }

            }
            if (isValid){
                totaltext.text="$total1"
            }
            else{
                Toast.makeText(this,"Please Enter The Quantity",Toast.LENGTH_LONG).show()
            }
            if (!shirt.isChecked && !tshirt.isChecked && !pants.isChecked && !shorts.isChecked ){
                totalcloth.text=null
                totaltext.text=null
                Toast.makeText(this,"Please Select The Clothes",Toast.LENGTH_LONG).show()
            }
        }

        ftotalbtn.setOnClickListener {
            val totaltext = totaltext.text.toString()
            val serviceText = servicetext.text.toString()
            val isFabricSelected = cotton.isChecked || silk.isChecked || wool.isChecked || nylon.isChecked
            if (totaltext.isEmpty() || serviceText.isEmpty() || !isFabricSelected) {
                ftotaltext.text=null
            } else {
                    val clothTotal  = totaltext.toDoubleOrNull() ?:0.0
                    val serviceTotal = serviceText.toDoubleOrNull() ?:0.0
                    val finalTotal = clothTotal + serviceTotal
                    val formattedTotal = finalTotal.toInt()
                    ftotaltext.text = "₹" + formattedTotal.toString()
                }

            var service1 = 0
            var isValid = true
            if (laundry.isChecked){
                val quality = totalcloth.text.toString().toIntOrNull()
                if (quality !=null && quality >0){
                    service1 += 20* quality
                }
                else{
                    isValid=false
                }
            }
            if (dry.isChecked){
                val quality = totalcloth.text.toString().toIntOrNull()
                if (quality !=null && quality >0){
                    service1 += 30* quality
                }else{
                    isValid=false
                }
            }
            if (iron.isChecked){
                val quality = totalcloth.text.toString().toIntOrNull()
                if (quality !=null && quality >0){
                    service1 += 25* quality
                }else{
                    isValid=false
                }
            }

            if (isValid){
                servicetext.text="$service1"
            }
            if (!laundry.isChecked && !dry.isChecked && !iron.isChecked){
                servicetext.text=null
                Toast.makeText(this,"Please Select The Service",Toast.LENGTH_LONG).show()
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