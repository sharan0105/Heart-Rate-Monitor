package com.example.heartratemonitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_finish.*

class Finish : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        btnFinish.setOnClickListener {
            finish()
            val it= Intent(this,Explore::class.java)
            startActivity(it)
        }
    }
}