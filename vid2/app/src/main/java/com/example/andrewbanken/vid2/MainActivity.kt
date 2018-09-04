package com.example.andrewbanken.vid2

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fileName = "video"
        val filePlace = "android.resource://" + packageName + "/raw/" + R.raw.salvideo


        val videoView = findViewById<View>(R.id.videoView) as VideoView
        videoView .setVideoURI(Uri.parse(filePlace))

        videoView.setMediaController(MediaController(this))
        videoView.start()
    }
}
