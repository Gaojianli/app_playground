package me.gaojianli.tiktokmsg

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MsgDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_msg_details)
        val index = intent.extras?.getInt("index")
        findViewById<TextView>(R.id.postion_text).text = "Index of item:${index}"
        findViewById<Button>(R.id.back_button).setOnClickListener {
            finish()
        }
    }
}
