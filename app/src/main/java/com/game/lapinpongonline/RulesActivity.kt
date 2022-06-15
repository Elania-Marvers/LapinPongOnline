package com.game.lapinpongonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RulesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)


        findViewById<Button>(R.id.home).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}