package com.example.andrew.andrewsvideoview

import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var playbackPostion= 0
    private var playbackPostion2= 0

    private val andrewVideo = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov"

    private lateinit var mediaController1: MediaController
    private lateinit var mediaController2: MediaController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaController1= MediaController(this)

        videoView.setOnPreparedListener {
            mediaController1.setAnchorView(videoContainer)
            videoView.setMediaController(mediaController1)
            videoView.seekTo(playbackPostion)
            videoView.start()
        }
        videoView.setOnInfoListener { player, what, extras ->
            if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                progressBar.visibility = View.INVISIBLE
                true
        }

        mediaController2= MediaController(this)
        val andrewVideo2= "android.resource://" + packageName + "/raw/" + R.raw.salvideo
        val videoView2 = findViewById<View>(R.id.videoView2) as VideoView
        videoView2.setVideoURI(Uri.parse(andrewVideo2))

        videoView2.setOnPreparedListener {
            mediaController2.setAnchorView(videoContainer2)
            videoView2.setMediaController(mediaController2)
            videoView2.seekTo(playbackPostion)
            videoView2.start()
        }

    }

    override fun onStart() {
        super.onStart()

        val uri = Uri.parse(andrewVideo)
        videoView.setVideoURI(uri)
        progressBar.visibility = View.VISIBLE


    }

    override fun onPause() {
        super.onPause()
        videoView.pause()
        playbackPostion = videoView.currentPosition

        super.onPause()
        videoView2.pause()
        playbackPostion2 = videoView2.currentPosition
    }

    override fun onStop() {
        videoView.stopPlayback()
        super.onStop()
        videoView2.stopPlayback()
        super.onStop()
    }
}
