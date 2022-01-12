package com.example.heartratemonitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_ecgmenu.*

class ECGMenu : AppCompatActivity() {
    private lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecgmenu)
        setSupportActionBar(tlbar)
        mAuth= FirebaseAuth.getInstance()
        val currUser=mAuth.currentUser
        Glide.with(this).load(currUser?.photoUrl).into(imgView)
        details.text="1)Make sure not to eat or drink anything for three to four hours before the test.\n\n" +
                "2)Don’t smoke on the day of the test because nicotine can interfere with your heart rate.\n\n" +
                "3)Don’t drink coffee or take any medications that contain caffeine without checking with your doctor.\n\n" +
                "4)Wear comfortable, loose-fitting clothes and good walking or running shoes.\n\n"
        lgOut.setOnClickListener {
            mAuth.signOut()
            val it=Intent(this,MainActivity::class.java)
            startActivity(it)
            finish()
        }
        startECG.setOnClickListener {
            Toast.makeText(this,"Starting the ECG",Toast.LENGTH_SHORT).show()
            val it= Intent(this,ECGGraph::class.java)
            startActivity(it)
                    }
    }
}