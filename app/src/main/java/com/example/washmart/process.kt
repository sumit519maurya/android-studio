package com.example.washmart

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class process : AppCompatActivity() {

    //img1
    private val rotationDuration: Long = 1000
    private val pauseDuration: Long = 2000

    //img2
    private val moveDuration: Long = 1000
    private val pauseDuration2: Long = 1000
    private val rightTravelFraction: Float = 0.5f
    private val leftTravelFraction: Float = 0.9f

    //img3
    private val bounceDuration: Long = 500
    private val scaleDuration: Long = 5000
    private val fadeDuration: Long = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_process)

        val imageView1 = findViewById<ImageView>(R.id.img1)
        val imageView2 = findViewById<ImageView>(R.id.img2)
        val imageView3 = findViewById<ImageView>(R.id.img3)
        val imageView4 = findViewById<ImageView>(R.id.logo)

        startRotationAnimation(imageView1)
        startMoveAnimation(imageView2)
        startPaymentAnimation(imageView3)
        startFlipAnimation(imageView4)
    }

    private fun startRotationAnimation(imageView: ImageView) {
        val rotation = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
        rotation.duration = rotationDuration

        rotation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                // Start pause timer after animation ends
                Handler(Looper.getMainLooper()).postDelayed({
                    startRotationAnimation(imageView)
                }, pauseDuration)
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })

        rotation.start()


    }

    private fun startMoveAnimation(imageView: ImageView) {
        // Ensure we get the width of the ImageView after it has been laid out
        imageView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // Remove the listener to avoid multiple calls
                imageView.viewTreeObserver.removeOnGlobalLayoutListener(this)

                // Calculate the travel distances
                val rightTravelDistance = imageView.width * rightTravelFraction
                val leftTravelDistance = imageView.width * leftTravelFraction

                // Set initial position off-screen to the left
                imageView.translationX = -leftTravelDistance

                val moveRight = ObjectAnimator.ofFloat(
                    imageView,
                    "translationX",
                    -leftTravelDistance,
                    rightTravelDistance
                )
                moveRight.duration = moveDuration

                val moveLeft = ObjectAnimator.ofFloat(
                    imageView,
                    "translationX",
                    rightTravelDistance,
                    -leftTravelDistance
                )
                moveLeft.duration = moveDuration

                val animatorSet = AnimatorSet()
                animatorSet.playSequentially(moveRight, moveLeft)

                // Create a ValueAnimator to repeat the AnimatorSet
                val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
                valueAnimator.duration = moveDuration + pauseDuration2
                valueAnimator.interpolator = null // No interpolation

                valueAnimator.addUpdateListener {
                    if (it.animatedFraction == 1f) {
                        animatorSet.start()
                    }
                }

                valueAnimator.repeatCount = ValueAnimator.INFINITE
                valueAnimator.repeatMode = ValueAnimator.RESTART

                valueAnimator.start()
            }
        })
    }

    private fun startPaymentAnimation(view: ImageView) {
        // Bounce animation
        val bounceAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f, -50f, 0f).apply {
            duration = 500 // 1 second
        }

        // Scale animation
        val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.2f, 1f).apply {
            duration = 1000 // 1 second
        }
        val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.2f, 1f).apply {
            duration = 1000 // 1 second
        }

        // Fade in and fade out animation
        val fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).apply {
            duration = 1000 // 0.5 second
        }
        val fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f).apply {
            duration = 1000 // 0.5 second
        }

        // Combine fade animations into a sequential AnimatorSet
        val fadeAnimatorSet = AnimatorSet().apply {
            playSequentially(fadeIn, fadeOut)
        }

        // Combine all animations into a single AnimatorSet
        val animatorSet = AnimatorSet().apply {
            playTogether(bounceAnimator, scaleXAnimator, scaleYAnimator, fadeAnimatorSet)
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    // Restart the animation when it ends
                    startPaymentAnimation(view)
                }
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
        }

        // Start the animation
        animatorSet.start()
    }
    private fun startFlipAnimation(view: ImageView) {
        // Flip animation
        val flipAnimator = ObjectAnimator.ofFloat(view, "rotationY", 0f, 360f).apply {
            duration = 2000 // 1 second for full flip
            repeatCount = ObjectAnimator.INFINITE // Repeat animation infinitely
            repeatMode = ObjectAnimator.RESTART // Restart animation after each repetition
        }

        // Start the animation
        flipAnimator.start()
    }

}