package com.example.washmart

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class billingpage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_billingpage)

        val tvSelectedCloths: TextView = findViewById(R.id.tvSelectedCloths)
        val tvSelectedFabric: TextView = findViewById(R.id.tvSelectedFabric)
        val tvTotalCloths: TextView = findViewById(R.id.tvTotalCloths)
        val tvSelectedService: TextView =findViewById(R.id.tvSelectedService)
        val tvFinalTotal: TextView = findViewById(R.id.tvFinalTotal)
        val tvClothPrice: TextView = findViewById(R.id.tvClothPrice)
        val tvServiceCharge: TextView = findViewById(R.id.tvServiceCharge)
        // val tvUserName: TextView =findViewById(R.id.tvUserName)
        // val tvUserPhone: TextView =findViewById(R.id.tvUserPhone)
        //  val tvOrderDate: TextView =findViewById(R.id.tvOrderDate)
        //  val tvUserAddress: TextView =findViewById(R.id.tvUserAddress)
        //  val savebtn:Button=findViewById(R.id.save)


        val totalCloth = intent.getStringExtra("Cloth_price")
        val servicecharge = intent.getStringExtra("service_charge")
        val totalcloths = intent.getStringExtra("Total_cloth")
        val ftotal = intent.getStringExtra("ftotal")
        val shirtChecked = intent.getStringExtra("shirt")
        val tshirtChecked = intent.getStringExtra("tshirt")
        val pantsChecked = intent.getStringExtra("pants")
        val shortsChecked = intent.getStringExtra("shorts")
        val cottonChecked = intent.getStringExtra("cotton")
        val woolChecked = intent.getStringExtra("wool")
        val silkChecked = intent.getStringExtra("silk")
        val nylonChecked = intent.getStringExtra("nylon")
        val laundryChecked = intent.getStringExtra("laundry")
        val dryChecked = intent.getStringExtra("dry")
        val ironChecked = intent.getStringExtra("iron")


        tvClothPrice.text = "₹" + totalCloth
        tvServiceCharge.text = "₹" + servicecharge
        tvTotalCloths.text = totalcloths
        tvFinalTotal.text =  ftotal
        val statusText = buildString {
            shirtChecked?.let {
                append(it)
                append("\n")
            }
            tshirtChecked?.let {
                append(it)
                append("\n")
            }
            pantsChecked?.let {
                append(it)
                append("\n")
            }
            shortsChecked?.let {
                append(it)
            }
        }
        tvSelectedCloths.text = statusText

        val statusfabric = buildString {
            cottonChecked?.let {
                append(it)
                append("\n")
            }
            woolChecked?.let {
                append(it)
                append("\n")
            }
            silkChecked?.let {
                append(it)
                append("\n")
            }
            nylonChecked?.let {
                append(it)
            }
        }
        tvSelectedFabric.text = statusfabric

        val statusservice = buildString {
            laundryChecked?.let {
                append(it)
                append("\n")
            }
            dryChecked?.let {
                append(it)
                append("\n")
            }
            ironChecked?.let {
                append(it)
            }
        }
        tvSelectedService.text = statusservice
    }
}