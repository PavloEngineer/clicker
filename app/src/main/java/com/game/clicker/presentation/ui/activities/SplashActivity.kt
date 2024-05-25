package com.game.clicker.presentation.ui.activities

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.game.clicker.R
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity: AppCompatActivity() {

    companion object {
        private const val PROPERTY_NAME = "progress"
        private const val DURATION_ANIMATION: Long = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val imageView = findViewById<ImageView>(R.id.imageView)

        val animator = createProgressBarAnimator(progressBar)
        setupAnimatorListeners(animator, imageView)
        animator.start()
    }

    private fun createProgressBarAnimator(progressBar: ProgressBar): ObjectAnimator {
        val animator = ObjectAnimator.ofInt(progressBar, PROPERTY_NAME, 0, 100)
        animator.duration = DURATION_ANIMATION
        return animator
    }

    private fun setupAnimatorListeners(animator: ObjectAnimator, imageView: ImageView) {
        animator.addUpdateListener { animation ->
            val progress = animation.animatedValue as Int
            val alpha = progress.toFloat() / 100f
            imageView.alpha = alpha
        }

        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                val intent = Intent(this@SplashActivity, AuthActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })
    }
}