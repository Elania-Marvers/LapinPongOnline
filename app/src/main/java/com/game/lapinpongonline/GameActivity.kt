package com.game.lapinpongonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.game.lapinpongonline.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idGame = intent.getStringExtra("id").toString()

        val query = MatchFirebaseRepo.getAllMatches()

        val option = FirestoreRecyclerOptions.Builder<MatchBean>()
            .setQuery(query, MatchBean::class.java)
            .setLifecycleOwner(this)
            .build()



        binding.rv.adapter = InGameFirebaseAdapter(option, idGame)
        binding.rv.layoutManager = GridLayoutManager(this, 1)






    }
}