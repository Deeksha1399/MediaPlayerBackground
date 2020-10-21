package com.example.mediaplayerbackground

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MusicPlayerService : Service() {

    private lateinit var myMusic: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        //myMusic=MediaPlayer.create(this,Settings.System.DEFAULT_RINGTONE_URI)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        myMusic = MediaPlayer.create(this, R.raw.mood)
        val musicPlayerApplication = MusicPlayerApplication()

        val notificationIntent = Intent(this, PlaySongActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notification = NotificationCompat.Builder(this, musicPlayerApplication.CHANNEL_ID)
                .setContentTitle("Media Player")
                .setSmallIcon(R.drawable.ic_music)
                .setContentIntent(pendingIntent)
                .build()
        Thread {
            myMusic.start()
            startForeground(1, notification)
        }.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        myMusic.stop()
    }


    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}