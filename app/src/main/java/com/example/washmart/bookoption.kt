package com.example.washmart

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
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
    private val windDuration: Long = 3000
    private var pulseAnimatorSet: AnimatorSet? = null
    private val activeAnimations = mutableMapOf<CheckBox, AnimatorSet>()
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
        val shirtimg:ImageView=findViewById(R.id.shirtimg)
        val tshirtimg:ImageView=findViewById(R.id.tshirtimg)
        val pantimg:ImageView=findViewById(R.id.pantimg)
        val shortimg:ImageView=findViewById(R.id.shortimg)
         shirtqt=findViewById(R.id.shirtqt)
         tshirtqt=findViewById(R.id.tshirtqt)
         pantqt=findViewById(R.id.paintqt)
         shortqt=findViewById(R.id.shortqt)
        totalcloth=findViewById(R.id.totalclothes)

        startWindAnimation(shirtimg)
        startWindAnimation(tshirtimg)
        startWindAnimation(pantimg)
        startWindAnimation(shortimg)




       back.setOnClickListener {
           startExplosionAnimation(back)
           val i= Intent(this,homepage::class.java)
           startActivity(i)
       }
        next.setOnClickListener {
            startExplosionAnimation(next)

            if (ftotaltext.text.isNullOrEmpty() || ftotaltext.text == "0") {
                Toast.makeText(this, "Please calculate the total before proceeding", Toast.LENGTH_SHORT).show()
            } else {    // Proceed with the intent if ftotaltext is not empty

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

        }




        total.setOnClickListener {
         //   startButtonAnimation(total)
            startExplosionAnimation(total)
            var total1 = 0
            var isValid = true
            val num1 = if (shirt.isChecked)shirtqt.text.toString().toInt()else 0
            val num2 =if (tshirt.isChecked) tshirtqt.text.toString().toInt()else 0
            val num3 =if (pants.isChecked) pantqt.text.toString().toInt()else 0
            val num4 =if (shorts.isChecked) shortqt.text.toString().toInt()else 0
            val sum = num1 + num2 + num3 + num4
            val result = sum

            if (shirt.isChecked) {
                startCombinationAnimation(shirt)
                totalcloth.text=result.toString()
                val quantity = shirtqt.text.toString().toIntOrNull()
                if (quantity != null && quantity > 0){
                total1 += 10 * quantity
            } else if (quantity==0){
                total1 -= 10 * quantity
                    Toast.makeText(this,"Please Enter The Quantity",Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(this,"Please Enter The Quantity",Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(this,"Please Enter The Quantity",Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(this,"Please Enter The Quantity",Toast.LENGTH_SHORT).show()
                }
                else{
                    isValid=false
                }

            }
            if (isValid){
                totaltext.text="$total1"
            }
            else{
                Toast.makeText(this,"Please Enter The Quantity",Toast.LENGTH_SHORT).show()
            }
            if (!shirt.isChecked && !tshirt.isChecked && !pants.isChecked && !shorts.isChecked ){
                totalcloth.text=null
                totaltext.text=null
                Toast.makeText(this,"Please Select The Clothes",Toast.LENGTH_SHORT).show()
            }
        }

        ftotalbtn.setOnClickListener {
            startExplosionAnimation(ftotalbtn)

            // Validate fabric selection
            val isFabricSelected = cotton.isChecked || silk.isChecked || wool.isChecked || nylon.isChecked
            if (!isFabricSelected) {
                ftotaltext.text = null
                Toast.makeText(this, "Please select a fabric", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Calculate service cost
            var service1 = 0
            var isValid = true
            val quality = totalcloth.text.toString().toIntOrNull()

            if (quality == null || quality <= 0) {
                isValid = false
                Toast.makeText(this, "Please enter a valid quantity of clothes", Toast.LENGTH_SHORT).show()
            } else {
                if (laundry.isChecked) {
                    service1 += 20 * quality
                }
                if (dry.isChecked) {
                    service1 += 30 * quality
                }
                if (iron.isChecked) {
                    service1 += 25 * quality
                }
            }

            // If no service is selected, show a message and return
            if (!laundry.isChecked && !dry.isChecked && !iron.isChecked) {
                servicetext.text = null
                Toast.makeText(this, "Please select a service", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (isValid) {
                servicetext.text = "$service1"
            }

            // Calculate total cost
            val totalTextValue = totaltext.text.toString()
            if (totalTextValue.isEmpty()) {
                ftotaltext.text = null
                Toast.makeText(this, "Please enter the total cloth cost", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val clothTotal = totalTextValue.toDoubleOrNull() ?: 0.0
            val finalTotal = clothTotal + service1
            val formattedTotal = finalTotal.toInt()
            ftotaltext.text = "₹$formattedTotal"
        }

        shirt.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                startCombinationAnimation(shirt)
            }else{
                stopPulseAnimation(shirt)
            }
        }
        tshirt.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                startCombinationAnimation(tshirt)
            }else{
                stopPulseAnimation(tshirt)
            }
        }
        pants.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                startCombinationAnimation(pants)
            }else{
                stopPulseAnimation(pants)
            }
        }
        shorts.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                startCombinationAnimation(shorts)
            }else{
                stopPulseAnimation(shorts)
            }
        }
        cotton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                startCombinationAnimation(cotton)
            }else{
                stopPulseAnimation(cotton)
            }
        }
        wool.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                startCombinationAnimation(wool)
            }else{
                stopPulseAnimation(wool)
            }
        }
        nylon.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                startCombinationAnimation(nylon)
            }else{
                stopPulseAnimation(nylon)
            }
        }
        silk.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                startCombinationAnimation(silk)
            }else{
                stopPulseAnimation(silk)
            }
        }
        dry.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                startCombinationAnimation(dry)
            }else{
                stopPulseAnimation(dry)
            }
        }
        laundry.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                startCombinationAnimation(laundry)
            }else{
                stopPulseAnimation(laundry)
            }
        }
        iron.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                startCombinationAnimation(iron)
            }else{
                stopPulseAnimation(iron)
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
    private fun startWindAnimation(view: ImageView) {
        // Horizontal movement to simulate wind blowing
        val moveRight = ObjectAnimator.ofFloat(view, "translationX", 0f, 50f)
        val moveLeft = ObjectAnimator.ofFloat(view, "translationX", 50f, 0f)
        moveRight.duration = windDuration
        moveLeft.duration = windDuration

        // Slight rotation to give dynamic effect
        val rotate = ObjectAnimator.ofFloat(view, "rotation", -5f, 5f)
        rotate.duration = windDuration / 2
        rotate.repeatCount = ObjectAnimator.INFINITE
        rotate.repeatMode = ObjectAnimator.REVERSE

        // Optional scaling to add depth
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.05f)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.05f)
        scaleX.duration = windDuration / 2
        scaleY.duration = windDuration / 2
        scaleX.repeatCount = ObjectAnimator.INFINITE
        scaleY.repeatCount = ObjectAnimator.INFINITE
        scaleX.repeatMode = ObjectAnimator.REVERSE
        scaleY.repeatMode = ObjectAnimator.REVERSE

        // AnimatorSet to play animations together
        val animatorSet = AnimatorSet().apply {
            playSequentially(moveRight, moveLeft)
            playTogether(rotate, scaleX, scaleY)
        }

        // Start the animation
        animatorSet.start()
    }
    private fun startButtonAnimation(button: Button) {
        // Scale animation
        val scaleX = ObjectAnimator.ofFloat(button, "scaleX", 1f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(button, "scaleY", 1f, 1.2f, 1f)
        scaleX.duration = 300 // Duration in milliseconds
        scaleY.duration = 300 // Duration in milliseconds

        // Start the animation
        scaleX.start()
        scaleY.start()
    }
    private fun startExplosionAnimation(button: Button) {
        // Scale animation
        val scaleX = ObjectAnimator.ofFloat(button, "scaleX", 1f, 2f)
        val scaleY = ObjectAnimator.ofFloat(button, "scaleY", 1f, 2f)
        scaleX.duration = 300
        scaleY.duration = 300

        // Rotation animation
        val rotation = ObjectAnimator.ofFloat(button, "rotation", 0f, 720f)
        rotation.duration = 500

        // Fade out animation
        val fadeOut = ObjectAnimator.ofFloat(button, "alpha", 1f, 0f)
        fadeOut.duration = 300

        // AnimatorSet to play animations together
        val animatorSet = AnimatorSet().apply {
            playTogether(scaleX, scaleY, rotation, fadeOut)
            // Optionally, add a listener to remove the button from the view after the animation
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {
                    button.alpha = 1f
                    button.scaleX = 1f
                    button.scaleY = 1f
                    button.rotation = 0f
                }

                override fun onAnimationCancel(animation: Animator) {}

                override fun onAnimationRepeat(animation: Animator) {}
            })
        }

        // Start the animation
        animatorSet.start()
    }
    private fun startCombinationAnimation(checkBox: CheckBox) {
    //colour change
      /*  val colorFrom = Color.WHITE
        val colorTo = Color.GREEN
        val colorAnimator = ObjectAnimator.ofArgb(checkBox, "backgroundColor", colorFrom, colorTo, colorFrom)
        colorAnimator.duration = 500

        colorAnimator.start()*/
//rotation
       /* val scaleX = ObjectAnimator.ofFloat(checkBox, "scaleX", 1f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(checkBox, "scaleY", 1f, 1.2f, 1f)
        scaleX.duration = 300
        scaleY.duration = 300

        // Rotate animation
        val rotate = ObjectAnimator.ofFloat(checkBox, "rotation", 0f, 360f)
        rotate.duration = 500

        // AnimatorSet to play animations together
        val animatorSet = AnimatorSet().apply {
            playTogether(scaleX, scaleY, rotate)
        }

        animatorSet.start()*/
//beat
        val scaleX = ObjectAnimator.ofFloat(checkBox, "scaleX", 1f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(checkBox, "scaleY", 1f, 1.2f, 1f)
        scaleX.duration = 300
        scaleY.duration = 300
        scaleX.repeatCount = ObjectAnimator.INFINITE
        scaleY.repeatCount = ObjectAnimator.INFINITE

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY)
        animatorSet.start()

        // Store the animation in the map
        activeAnimations[checkBox] = animatorSet
//normal
       /* val scaleX = ObjectAnimator.ofFloat(checkBox, "scaleX", 1f, 1.5f, 1f)
        val scaleY = ObjectAnimator.ofFloat(checkBox, "scaleY", 1f, 1.5f, 1f)
        scaleX.duration = 300
        scaleY.duration = 300

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY)
        animatorSet.start()*/
    }
    private fun stopPulseAnimation(checkBox: CheckBox) {
        val animatorSet = activeAnimations[checkBox]
        animatorSet?.cancel()
        activeAnimations.remove(checkBox)
    }


}