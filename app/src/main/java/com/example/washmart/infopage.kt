package com.example.washmart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class infopage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_infopage)
        val save:Button=findViewById(R.id.save)

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




        save.setOnClickListener {

            startActivity(
                Intent(this,billingpage::class.java)
                .putExtra("Cloth_price",totalCloth.toString())
                .putExtra("service_charge",servicecharge.toString())
                .putExtra("Total_cloth",totalcloths.toString())
                .putExtra("ftotal",ftotal.toString())
                .putExtra("shirt", shirtChecked)
                .putExtra("tshirt", tshirtChecked)
                .putExtra("pants", pantsChecked)
                    .putExtra("shorts", shortsChecked)
                    .putExtra("cotton", cottonChecked)
                    .putExtra("wool", woolChecked)
                    .putExtra("silk", silkChecked)
                    .putExtra("nylon", nylonChecked)
                    .putExtra("laundry", laundryChecked)
                    .putExtra("dry", dryChecked)
                    .putExtra("iron", ironChecked))

        }

    }
}