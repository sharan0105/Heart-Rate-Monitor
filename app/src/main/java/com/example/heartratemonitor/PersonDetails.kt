package com.example.heartratemonitor
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_person_details.*
import org.json.JSONArray

class PersonDetails : AppCompatActivity() {
    companion object{
        var name:String="Heena"
        val weight:String="23"
        var uid=name+weight
    }
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database:FirebaseDatabase
    private lateinit var reference:DatabaseReference
    var arr:ArrayList<Double> = arrayListOf(1.19,2.22,3.34,4.44,5.65)
    var bloodGrp:String?=null
    var bloodType= arrayListOf("A+","A-","B+","B-","O+","O-","AB+","AB-")
    override fun onCreate(savedInstanceState: Bundle?) {
        setSupportActionBar(newTool)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)
        mAuth= FirebaseAuth.getInstance()
        val currentUser=mAuth.currentUser
        Glide.with(this).load(currentUser?.photoUrl).into(userImg)
        database= FirebaseDatabase.getInstance()
        reference=database.getReference("Users")
        val arrayAdapter=ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,bloodType)
        bldGrp.adapter=arrayAdapter
        bldGrp.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?,position: Int,id: Long)
            {
                bloodGrp=bloodType[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            Toast.makeText(this@PersonDetails,"Please select your Blood Group",Toast.LENGTH_SHORT).show()
            }
        }
        btnCont.setOnClickListener {
            val email=mAuth.currentUser!!.email
            name=etName.text.toString()
            val age=etAge.text.toString().toInt()
            val ft=etHt.text.toString().toInt()
            var g="Male"
            if(Female.isChecked)
            {g="Female"}
            val inch=etInch.text.toString().toInt()
            val height=ft*30.48+(inch*2.54)
            val x=height.toString()
            val weight=etWeight.text.toString()
            val model=Person(email,name,age,g,weight,x,arr)
            reference.child(name).setValue(model)
            Toast.makeText(this@PersonDetails,"Data submitted",Toast.LENGTH_SHORT).show()
             val intent= Intent(this@PersonDetails,Explore::class.java)
             startActivity(intent)
             etName.setText("")
             etAge.setText("")
             etHt.setText("")
             etInch.setText("")
             etWeight.setText("")
             finish()
        }
        btnSignOut.setOnClickListener {
            mAuth.signOut()
            val it=Intent(this,MainActivity::class.java)
            startActivity(it)
            finish()
        }
    }
}