package com.example.washmart

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class homepage : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle

    private val moveDuration: Long = 5000 // 5 seconds
    private val bounceDuration: Long = 1000 // 1 second

 //   private val flipDuration: Long = 1000 // 1 second for flip
  //  private val blinkDuration: Long = 500

    private val flipDuration: Long = 1000 // 1 second for flip
    private val pauseDuration: Long = 2000 // 2 seconds for pause


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homepage)

        val booknowimgae: ImageView = findViewById(R.id.booknowimage)
        val booknowtext: TextView = findViewById(R.id.booknowtext)
        val img2: ImageView = findViewById(R.id.imageView2)
        val processimg: ImageView = findViewById(R.id.imageView6)
        val historyimg: ImageView = findViewById(R.id.imageView7)
        val profileimg: ImageView = findViewById(R.id.profileimg)
        val profile: TextView = findViewById(R.id.profile)

        val drawerLayout: DrawerLayout = findViewById(R.id.main)
        val history1: RelativeLayout = findViewById(R.id.history)
        val processs: RelativeLayout = findViewById(R.id.processs)
        val navigation: NavigationView = findViewById(R.id.navigation)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

      //  startDeliveryAnimation(img2)
        startFlipAnimation(booknowimgae)
        startFlipAnimation(processimg)
        startFlipAnimation(historyimg)
        startFlipAnimation(profileimg)

        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.opendrawer,
            R.string.closedrawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigation.setNavigationItemSelectedListener { item ->
            val id = item.itemId
            if (id == R.id.home) {
                Toast.makeText(this, "Home selected", Toast.LENGTH_LONG).show()
            } else if (id == R.id.profile) {
                // Handle profile
            } else if (id == R.id.order) {
                // Handle order
            } else if (id == R.id.help) {
                // Handle help
            } else if (id == R.id.logout) {
                // Handle logout
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        booknowimgae.setOnClickListener {
            val i = Intent(this, bookoption::class.java)
            startActivity(i)
        }

        booknowtext.setOnClickListener {
            val i = Intent(this, bookoption::class.java)
            startActivity(i)
        }

        profileimg.setOnClickListener {
            val i = Intent(this, profilepage::class.java)
            startActivity(i)
        }

        profile.setOnClickListener {
            val i = Intent(this, profilepage::class.java)
            startActivity(i)
        }

        history1.setOnClickListener {
            val i = Intent(this, history::class.java)
            startActivity(i)
        }

        processs.setOnClickListener {
            val i = Intent(this, process::class.java)
            startActivity(i)
        }
    }

    private fun startDeliveryAnimation(view: ImageView) {
        // Vehicle movement animation
        val moveRight = ObjectAnimator.ofFloat(view, "translationX", 0f, 1000f) // Move right
        val moveLeft = ObjectAnimator.ofFloat(view, "translationX", 1000f, 0f) // Move back to start
        moveRight.duration = moveDuration
        moveLeft.duration = moveDuration

        // Delivery item bounce animation
        val bounce = ObjectAnimator.ofFloat(view, "translationY", 0f, -30f, 0f)
        bounce.duration = bounceDuration
        bounce.repeatCount = ObjectAnimator.INFINITE
        bounce.repeatMode = ObjectAnimator.REVERSE

        // Set up AnimatorSet to play animations
        val vehicleAnimatorSet = AnimatorSet()
        vehicleAnimatorSet.playSequentially(moveRight, moveLeft)
        vehicleAnimatorSet.start()

        bounce.start()
    }

    private fun startFlipAnimation(view: ImageView) {
        // Flip animation
        val flipAnimator = ObjectAnimator.ofFloat(view, "rotationY", 0f, 360f).apply {
            duration = flipDuration
        }

        // Pause animation
        val pauseAnimator = ObjectAnimator.ofFloat(view, "rotationY", 0f).apply {
            duration = pauseDuration
        }

        // AnimatorSet to sequence flip and pause animations
        val animatorSet = AnimatorSet().apply {
            playSequentially(flipAnimator, pauseAnimator)
        }

        // Start the animation and schedule the next run
        val handler = Handler(mainLooper)
        val runnable = object : Runnable {
            override fun run() {
                animatorSet.start()
                handler.postDelayed(this, flipDuration + pauseDuration) // Schedule next run
            }
        }

        handler.post(runnable) // Start the first run
    }
}
