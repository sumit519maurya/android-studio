package com.example.washmart

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class timingfragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_timingfragment, container, false)
        val logo: ImageView =view.findViewById(R.id.imageView)
      //  val text: TextView =view.findViewById(R.id.textView)
      //  startFlipAnimation(logo)
       // startHangingAnimation(text)

   return view
    }
    private fun startFlipAnimation(view: ImageView) {

        val flipAnimator = ObjectAnimator.ofFloat(view, "rotationY", 0f, 360f).apply {
            duration = 2000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
        }
        flipAnimator.start()
    }
    private fun startHangingAnimation(textView: TextView) {
        val rotateAnimator = ObjectAnimator.ofFloat(textView, "rotation", -10f, 10f).apply {
            duration = 1000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = AccelerateDecelerateInterpolator()
        }


        val translateAnimator = ObjectAnimator.ofFloat(textView, "translationY", -10f, 10f).apply {
            duration = 1000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = AccelerateDecelerateInterpolator()
        }


        val scaleXAnimator = ObjectAnimator.ofFloat(textView, "scaleX", 1f, 1.05f, 1f).apply {
            duration = 1000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }

        val scaleYAnimator = ObjectAnimator.ofFloat(textView, "scaleY", 1f, 1.05f, 1f).apply {
            duration = 1000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }


        val animatorSet = AnimatorSet().apply {
            playTogether(rotateAnimator, translateAnimator, scaleXAnimator, scaleYAnimator)
            start() // Start the animation
        }
    }
    }
