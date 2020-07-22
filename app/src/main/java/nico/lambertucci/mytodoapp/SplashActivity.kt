package nico.lambertucci.mytodoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import nico.lambertucci.mytodoapp.ui.MainActivity

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this,
                MainActivity::class.java))
            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}