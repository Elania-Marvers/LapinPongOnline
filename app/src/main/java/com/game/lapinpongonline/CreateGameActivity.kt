package com.game.lapinpongonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.game.lapinpongonline.databinding.ActivityCreateGameBinding
import com.game.lapinpongonline.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class CreateGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAdd.setOnClickListener {
            if (binding.etTeam1.text.isNullOrBlank()) {
                binding.etTeam1.error = "Le champs est vide"
            } else if (binding.etTeam2.text.isNullOrBlank()) {
                binding.etTeam2.error = "Le champs est vide"
            } else if (FirebaseAuth.getInstance().currentUser == null) {
                finish()
            } else {
                val match = MatchBean(
                    getRandomString(10),
                    binding.etTeam1.text.toString(),
                    binding.etTeam2.text.toString(),
                    FirebaseAuth.getInstance().currentUser?.uid,
                    0,
                    0,
                    Date().time,
                    90,
                    false
                )

                MatchFirebaseRepo.create(match)
                    .addOnSuccessListener {
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Fail : ${it.message}", Toast.LENGTH_LONG).show()
                        it.printStackTrace()
                    }
            }
        }

    }

    fun getRandomString(length: Int): String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
}