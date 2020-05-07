package com.bytedance.videoplayer

import android.content.Intent
import android.content.res.Configuration
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.net.Uri.*
import android.net.VpnService.prepare
import android.support.v7.app.AppCompatActivity
import android.os.PersistableBundle
import android.util.Log
import android.os.Bundle
import android.os.Environment
import android.view.WindowManager
import android.widget.MediaController
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.VideoView

class MainActivity:AppCompatActivity() {
    private var mediaController:MediaController? = null
    private var position=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Utils.verifyStoragePermissions(this)
        var uri: Uri? = if(Intent.ACTION_VIEW == intent.action)
            Uri.parse(intent.dataString)
        else
            intent.getParcelableExtra<Uri?>("VideoUri")

        if(uri == null)
        {
            Toast.makeText(this,"No uri specified!",Toast.LENGTH_LONG).show()
            return
        }
        if(mediaController == null){
            mediaController = MediaController(this@MainActivity)
        }
        position = savedInstanceState?.getInt("Position") ?: 0
        hideActionBar()
        try{
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(uri)
        }catch (e:Exception){
            Log.e("Error",e.message!!)
        }
        videoView.requestFocus()
        videoView.seekTo(position)
        videoView.start()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt("Position",position)
        videoView.pause()
        super.onSaveInstanceState(outState!!)
    }

    override fun onPause() {
        super.onPause()
        position=videoView.currentPosition
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if(savedInstanceState != null){
            position = savedInstanceState.getInt("Position")
            videoView.seekTo(position)
        }
        hideActionBar()
    }

    private fun hideActionBar(){
        val configuration=resources.configuration
        if(configuration.orientation==Configuration.ORIENTATION_LANDSCAPE){
            supportActionBar?.hide()
        }else {
            supportActionBar?.show()
        }
    }
}