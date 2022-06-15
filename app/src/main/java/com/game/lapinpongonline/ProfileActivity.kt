package com.game.lapinpongonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.game.lapinpongonline.databinding.ActivityMainBinding
import com.game.lapinpongonline.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val TAG = "GOOGLE SIGN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        val option = FirestoreRecyclerOptions.Builder<MatchBean>()
            .setQuery(MatchFirebaseRepo.getAllMatches(), MatchBean::class.java)
            .setLifecycleOwner(this)
            .build()

        binding.rv.adapter = GameFirebaseAdapter(option)
        binding.rv.layoutManager = GridLayoutManager(this, 1)


        binding.logOutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

        binding.createMatch.setOnClickListener {
            startActivity(Intent(this, CreateGameActivity::class.java))
        }

    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            binding.emailTv.text = firebaseUser.displayName
        }
    }
}