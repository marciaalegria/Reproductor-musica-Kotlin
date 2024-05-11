package com.example.reproductormusicakotlin

import android.media.MediaPlayer
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.reproductormusicakotlin.AppConstans.Companion.LOG_MAIN_ACTIVITY
import com.example.reproductormusicakotlin.AppConstans.Companion.MEDIA_PLAYER_POSITION
import com.example.reproductormusicakotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var mediaPlayer: MediaPlayer?=null

    private var position:Int = 0
    private lateinit var currentSong:song

    private var currentSongIndex:Int = 0
    private var isPlaying:Boolean= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(LOG_MAIN_ACTIVITY, "onCreate()")
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentSong= AppConstans.songs[currentSongIndex]

        savedInstanceState.let{
            if (it != null) {
                position= it.getInt(MEDIA_PLAYER_POSITION)
            }
        }
        updateUisong()
        binding.playPauseButton.setOnClickListener{playOrPauseMusic()}

    }

    override fun onStart() {
        super.onStart()
        Log.i(LOG_MAIN_ACTIVITY, "onStart()")
        mediaPlayer = MediaPlayer.create(this,currentSong.audioResId)
        if(isPlaying)  mediaPlayer?.start()



    }

    override fun onResume() {
        super.onResume()
        Log.i(LOG_MAIN_ACTIVITY, "onResume()")
        if(isPlaying){
            mediaPlayer?.seekTo(position)
            mediaPlayer?.start()
            isPlaying=!isPlaying

        }


    }

    override fun onPause() {
        super.onPause()
        Log.i(LOG_MAIN_ACTIVITY, "onPause()")
        if (mediaPlayer != null)
            position = mediaPlayer!!.currentPosition
        mediaPlayer?.pause()
        //isPlaying=false
    }

    override fun onStop() {
        super.onStop()
        Log.i(LOG_MAIN_ACTIVITY, "onStop()")
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(LOG_MAIN_ACTIVITY, "onRestart()")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(LOG_MAIN_ACTIVITY, "onDestroy()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MEDIA_PLAYER_POSITION, position)
    }
    private fun updateUisong(){
        binding.titleTextView.text = currentSong.title
        binding.albumCoverImageView.setImageResource(currentSong.imageResId)
    }
    private fun playOrPauseMusic(){
        if(isPlaying){
            mediaPlayer?.pause()
        }else{
            mediaPlayer?.start()
        }
        isPlaying = !isPlaying
    }

}