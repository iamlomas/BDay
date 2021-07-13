package com.iamlomas.bday

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View.TEXT_ALIGNMENT_CENTER
import androidx.appcompat.app.AppCompatActivity
import com.lomas.androidSplashScreen.AndroidSplashScreen

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen: AndroidSplashScreen = AndroidSplashScreen(this).apply {
            withFullScreen()
            withTargetActivity(MainActivity::class.java)
            withSplashTimeOut(2500)
            withBackgroundColor(Color.WHITE)
            withLogo(R.drawable.celebration)
            withBelowLogoText("BDay")
            withFooterText("Created by Lomas")
        }

        splashScreen.apply {
            tvBelowLogoText!!.textAlignment = TEXT_ALIGNMENT_CENTER
            tvBelowLogoText!!.setTextColor(Color.BLACK)
            tvBelowLogoText!!.textSize = 25.0F
            tvFooter!!.setTextColor(Color.BLACK)
            tvFooter!!.textSize = 15.0F
            tvBelowLogoText!!.typeface =
                Typeface.createFromAsset(assets, "fonts/lucidaCalligraphy.ttf")
        }

        setContentView(splashScreen.create())
    }
}
