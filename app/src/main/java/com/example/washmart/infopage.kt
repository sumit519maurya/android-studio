package com.example.washmart

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class infopage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_infopage)
        val save:Button=findViewById(R.id.save)

        val usname:TextView=findViewById(R.id.usname)
        val phoneno:TextView=findViewById(R.id.phoneno)
        val orderdate:TextView=findViewById(R.id.orderdate)
        val address:TextView=findViewById(R.id.address)


        val sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "N/A")
        val phoneNumber = sharedPref.getString("phoneNumber", "N/A")


        val totalCloth = intent.getStringExtra("Cloth_price")
        val servicecharge = intent.getStringExtra("service_charge")
        val totalcloths = intent.getStringExtra("Total_cloth")
        val ftotal = intent.getStringExtra("ftotal")
        val tshirtChecked = intent.getStringExtra("tshirt")
        val shortsChecked = intent.getStringExtra("shorts")
        val cottonChecked = intent.getStringExtra("cotton")
        val woolChecked = intent.getStringExtra("wool")
        val silkChecked = intent.getStringExtra("silk")
        val nylonChecked = intent.getStringExtra("nylon")
        val laundryChecked = intent.getStringExtra("laundry")
        val dryChecked = intent.getStringExtra("dry")
        val ironChecked = intent.getStringExtra("iron")
        val shirtt = intent.getStringExtra("shirt")
        val pant = intent.getStringExtra("pants")


        usname.text = username
        phoneno.text = phoneNumber

        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val formattedDateTime = dateFormat.format(currentDateTime)
        orderdate.setText(formattedDateTime)

        save.setOnClickListener {
            val intent = Intent(this, billingpage::class.java).apply {
                putExtra("Cloth_price", totalCloth)
                putExtra("service_charge", servicecharge)
                putExtra("Total_cloth", totalcloths)
                putExtra("ftotal", ftotal)
                if (shirtt!=null){
               putExtra("shirt",shirtt)
                }
                if (pant!=null){
                    putExtra("pants",pant)
                }
                if (tshirtChecked!=null){
                    putExtra("tshirt",tshirtChecked)
                }
                if (shortsChecked!=null){
                    putExtra("shorts",shortsChecked)
                }
                if (cottonChecked!=null){
                    putExtra("cotton",cottonChecked)
                }
                if (woolChecked!=null){
                    putExtra("wool",woolChecked)
                }
                if (silkChecked!=null){
                    putExtra("silk",silkChecked)
                }
                if (nylonChecked!=null){
                    putExtra("nylon",nylonChecked)
                }
                if (laundryChecked!=null){
                    putExtra("laundry",laundryChecked)
                }
                if (dryChecked!=null){
                    putExtra("dry",dryChecked)
                }
                if (ironChecked!=null){
                    putExtra("iron",ironChecked)
                }
                putExtra("username", usname.text.toString())
                putExtra("phoneno", phoneno.text.toString())
                putExtra("datetime", orderdate.text.toString())
                putExtra("address", address.text.toString())
            }
            startActivity(intent)

        }

    }
}