package com.example.washmart

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class billingpage : AppCompatActivity() {
    private lateinit var dbHelper: Dbhelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_billingpage)

        dbHelper = Dbhelper(this)


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

        val totalCloth = intent.getStringExtra("Cloth_price")
        val servicecharge = intent.getStringExtra("service_charge")
        val totalcloths = intent.getStringExtra("Total_cloth")
        val ftotal = intent.getStringExtra("ftotal")
        val shirtChecked = intent.getStringExtra("shirt")?.isNotEmpty() == true
        val tshirtChecked = intent.getStringExtra("tshirt")?.isNotEmpty() == true
        val pantsChecked = intent.getStringExtra("pants")?.isNotEmpty() == true
        val shortsChecked = intent.getStringExtra("shorts")?.isNotEmpty() == true
        val cottonChecked = intent.getStringExtra("cotton")?.isNotEmpty() == true
        val woolChecked = intent.getStringExtra("wool")?.isNotEmpty() == true
        val silkChecked = intent.getStringExtra("silk")?.isNotEmpty() == true
        val nylonChecked = intent.getStringExtra("nylon")?.isNotEmpty() == true
        val laundryChecked = intent.getStringExtra("laundry")?.isNotEmpty() == true
        val dryChecked = intent.getStringExtra("dry")?.isNotEmpty() == true
        val ironChecked = intent.getStringExtra("iron")?.isNotEmpty() == true
        val username = intent.getStringExtra("username")
        val phoneno = intent.getStringExtra("phoneno")
        val datetime = intent.getStringExtra("datetime")
        val address = intent.getStringExtra("address")
       // val userid: Int = intent.getIntExtra("userId", -1)

        tvClothPrice.text = "₹" + totalCloth
        tvServiceCharge.text = "₹" + servicecharge
        tvTotalCloths.text = totalcloths +" "+ "Cloth"
        tvFinalTotal.text = ftotal
        tvUserName.text = username
        tvOrderDate.text = datetime
        tvUserPhone.text = phoneno
        tvUserAddress.text = address

        val statusText = buildString {
            if (shirtChecked) append("Shirt\n")
            if (tshirtChecked) append("Tshirt\n")
            if (pantsChecked) append("Pants\n")
            if (shortsChecked) append("Shorts")
        }
        tvSelectedCloths.text = statusText

        val statusfabric = buildString {
            if (cottonChecked) append("Cotton\n")
            if (woolChecked) append("Wool\n")
            if (silkChecked) append("Silk\n")
            if (nylonChecked) append("Nylon")
        }
        tvSelectedFabric.text = statusfabric

        val statusservice = buildString {
            if (laundryChecked) append("Laundry\n")
            if (dryChecked) append("Dry\n")
            if (ironChecked) append("Iron")
        }
        tvSelectedService.text = statusservice

        val sharedPref = this.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val userid =sharedPref.getLong("userid",1L)
        if (userid != null) {
            val isSaved = dbHelper.saveBill(
                username ?: "",
                userid,
                totalCloth,
                servicecharge,
                totalcloths,
                ftotal,
                datetime,
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
