package com.example.washmart

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class billingpage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var dbHelper: Dbhelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billingpage)
        dbHelper = Dbhelper(this)

        // TextViews in the layout
        val tvSelectedCloths: TextView = findViewById(R.id.tvSelectedCloths)
        val tvSelectedFabric: TextView = findViewById(R.id.tvSelectedFabric)
        val tvTotalCloths: TextView = findViewById(R.id.tvTotalCloths)
        val tvSelectedService: TextView = findViewById(R.id.tvSelectedService)
        val tvFinalTotal: TextView = findViewById(R.id.tvFinalTotal)
        val tvClothPrice: TextView = findViewById(R.id.tvClothPrice)
        val tvServiceCharge: TextView = findViewById(R.id.tvServiceCharge)
        val tvUserName: TextView = findViewById(R.id.tvUserName)
        val tvUserPhone: TextView = findViewById(R.id.tvUserPhone)
        val tvOrderDate: TextView = findViewById(R.id.tvOrderDate)
        val tvUserAddress: TextView = findViewById(R.id.tvUserAddress)

        // Retrieve Intent extras
        val totalCloth =intent.getStringExtra("Cloth_price") ?: ""
        val serviceCharge = intent.getStringExtra("service_charge") ?: ""
        val totalCloths = intent.getStringExtra("Total_cloth") ?: ""
        val finalTotal = intent.getStringExtra("ftotal") ?: ""

        val shirtChecked = intent.getStringExtra("shirt") != null
        val tshirtChecked = intent.getStringExtra("tshirt") != null
        val pantsChecked = intent.getStringExtra("pants") != null
        val shortsChecked = intent.getStringExtra("shorts") != null

        val cottonChecked = intent.getStringExtra("cotton")!=null
        val woolChecked = intent.getStringExtra("wool")!=null
        val silkChecked = intent.getStringExtra("silk")!=null
        val nylonChecked = intent.getStringExtra("nylon")!=null
        val laundryChecked = intent.getStringExtra("laundry")!=null
        val dryChecked = intent.getStringExtra("dry")!=null
        val ironChecked = intent.getStringExtra("iron")!=null

        val username = intent.getStringExtra("username") ?: ""
        val phoneno = intent.getStringExtra("phoneno") ?: ""
        val orderdate = intent.getStringExtra("datetime") ?: ""
        val address = intent.getStringExtra("address") ?: ""

        // Display values on the billing page
        tvClothPrice.text = "₹$totalCloth"
        tvServiceCharge.text = "₹$serviceCharge"
        tvTotalCloths.text = totalCloths
        tvFinalTotal.text = finalTotal
        tvUserName.text = username
        tvUserPhone.text = phoneno
        tvOrderDate.text = orderdate
        tvUserAddress.text = address

        // Build selected cloths string

        val selectedClothes = mutableListOf<String>()

        if (shirtChecked) {
            selectedClothes.add("Shirt")
        }
        if (pantsChecked) {
            selectedClothes.add("Pant")
        }
        if (tshirtChecked) {
            selectedClothes.add("Tshirt")
        }
        if (shortsChecked) {
            selectedClothes.add("Shorts")
        }

        tvSelectedCloths.text = if (selectedClothes.isNotEmpty()) {
            selectedClothes.joinToString("\n") // Join selected items with commas
        } else {
            "No clothes selected"
        }


        // Build selected fabric string
        val statusFabric = mutableListOf<String>()

        if (cottonChecked) {
            statusFabric.add("Cotton")
        }
        if (silkChecked) {
            statusFabric.add("Silk")
        }
        if (woolChecked) {
            statusFabric.add("Wool")
        }
        if (nylonChecked) {
            statusFabric.add("Nylon")
        }

        tvSelectedFabric.text = if (statusFabric.isNotEmpty()) {
            statusFabric.joinToString("\n") // Join selected items with commas
        } else {
            "No Fabric selected"
        }

        // Build selected services string
        val statusService = mutableListOf<String>()

        if (laundryChecked) {
            statusService.add("Laundry")
        }
        if (dryChecked) {
            statusService.add("Dry Cleaning")
        }
        if (ironChecked) {
            statusService.add("Iron")
        }

        tvSelectedService.text = if (statusService.isNotEmpty()) {
            statusService.joinToString("\n") // Join selected items with commas
        } else {
            "No clothes selected"
        }
        // Save the bill in the database
        val sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val userid = sharedPref.getLong("userid", 1L) // Assuming userid is stored in shared preferences

        if (userid != null) {
            val isSaved = dbHelper.saveBill(
                username,
                userid,
                totalCloth,
                serviceCharge,
                totalCloths,
                finalTotal,
                orderdate,
                phoneno,
                address,
                shirtChecked,
                tshirtChecked,
                pantsChecked,
                shortsChecked,
                cottonChecked,
                woolChecked,
                silkChecked,
                nylonChecked,
                laundryChecked,
                dryChecked,
                ironChecked
            )
            if (isSaved) {
                Toast.makeText(this, "Bill saved successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to save bill", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "User ID is missing", Toast.LENGTH_SHORT).show()
        }
    }
}
