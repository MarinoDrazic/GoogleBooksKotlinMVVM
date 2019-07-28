package com.zebra.isv.googlebookskotlinmvvm.splashScreen

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import com.zebra.isv.googlebookskotlinmvvm.MainActivity
import com.zebra.isv.googlebookskotlinmvvm.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_splash)
        animateSplashLogo()

    }

    private fun animateSplashLogo(): ObjectAnimator {
        val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
            splash_logo,
            PropertyValuesHolder.ofFloat("scaleX", 0.90f),
            PropertyValuesHolder.ofFloat("scaleY", 0.90f)
        )
        scaleDown.duration = 300
        scaleDown.repeatCount = 3
        scaleDown.repeatMode = ValueAnimator.REVERSE
        scaleDown.interpolator = LinearInterpolator()
        scaleDown.start()

        scaleDown.apply {
            doOnEnd {
                goToBaseActivity()
            }
        }
        return scaleDown
    }

    private fun goToBaseActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
