package com.example.heartratemonitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_exercise.*

class ExerciseBegin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        btnStartAcitivty.setOnClickListener {
            val it= Intent(this@ExerciseBegin,Exercise2::class.java)
            startActivity(it)
        }
    }
}