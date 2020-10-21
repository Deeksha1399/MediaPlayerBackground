package com.example.mediaplayerbackground

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class PlaySongActivity : AppCompatActivity() {

    lateinit var playMusic: Button
    lateinit var stopMusic: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playMusic = findViewById(R.id.btnPlayMusic)
        stopMusic = findViewById(R.id.btnStopMusic)

        playMusic.setOnClickListener {
            val musicPlayerService = Intent(this, MusicPlayerService::class.java)
            //startService(musicPlayerService)
            ContextCompat.startForegroundService(this, musicPlayerService)
            Toast.makeText(this, "Playing", Toast.LENGTH_SHORT).show()
        }

        stopMusic.setOnClickListener {
            stopService(Intent(this, MusicPlayerService::class.java))
            Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show()
        }

    }
}