package com.bytedance.videoplayer

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.bytedance.videoplayer.Utils.verifyStoragePermissions


class PickFile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_file)
        findViewById<Button>(R.id.pick_file_button).setOnClickListener {
            verifyStoragePermissions(this)
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "video/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent,1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri? = data?.data
            val intent=Intent(this, MainActivity::class.java)
            intent.putExtra("VideoUri",uri)
            startActivity(intent)
        }
    }
}