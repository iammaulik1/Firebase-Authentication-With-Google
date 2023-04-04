package com.example.firebaseauthenticationwithgoogle

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // it hides Action bar
        supportActionBar?.hide()
        auth=FirebaseAuth.getInstance()

        val user = auth.currentUser
        if (user!=null){
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)

                finish()
            },3000)
        }else{

        // go to Login Activity after 3 seconds

        Handler(Looper.getMainLooper()).postDelayed({
          val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)

            finish()
        },3000)
    }
}
}