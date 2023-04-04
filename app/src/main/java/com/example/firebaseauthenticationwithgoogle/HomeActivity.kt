package com.example.firebaseauthenticationwithgoogle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseauthenticationwithgoogle.databinding.ActivityHomeBinding
import com.example.firebaseauthenticationwithgoogle.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this,gso)

        binding.buttonSignOut.setOnClickListener {

            // Sign Out in Firebase
            auth.signOut()

            // SignOut From Google Client
            //Without it you won't be able to select account again even after using auth
            googleSignInClient.signOut()

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)

            finish()
        }

        binding.buttonDeleteAccount.setOnClickListener {
            val user =auth.currentUser

            user?.delete()?.addOnCompleteListener {
                if (it.isSuccessful){
                    googleSignInClient.signOut()

                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)

                    finish()
                }
            }
        }

    }
}