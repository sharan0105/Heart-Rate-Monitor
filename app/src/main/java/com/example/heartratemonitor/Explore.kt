package com.example.heartratemonitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_explore.*
import kotlinx.android.synthetic.main.activity_explore.userImg
import kotlinx.android.synthetic.main.activity_person_details.*

class Explore : AppCompatActivity() {
    private lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)
        setSupportActionBar(newToolbar)
        mAuth= FirebaseAuth.getInstance()
        val currentUser=mAuth.currentUser
        Glide.with(this).load(currentUser?.photoUrl).into(userImg)
        btnBMI.setOnClickListener {
            val it= Intent(this@Explore,BMICalc::class.java)
            startActivity(it)
        }
        btnECG.setOnClickListener {
            val it=Intent(this,ECGMenu::class.java)
            startActivity(it);
        }
        btnExercise.setOnClickListener {
            val it=Intent(this,ExerciseBegin::class.java)
            startActivity(it);
        }
        signOut.setOnClickListener {
            mAuth.signOut()
            val it=Intent(this,MainActivity::class.java)
            startActivity(it)
            finish()
        }
    }
}