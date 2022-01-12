package com.example.heartratemonitor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_bmicalc.*
import kotlinx.android.synthetic.main.activity_ecggraph.*
import java.math.BigDecimal
import java.math.RoundingMode
class BMICalc : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private var ht:String="1"
    private var wt:String="1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmicalc)
        var name=PersonDetails.name
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(name).get().addOnSuccessListener {
            if (it.exists())
            {
                ht=it.child("height").value.toString()  //height in cm //
                wt=it.child("weight").value.toString()  // wt in kg //
                etHt.setText("$ht")
                etWt.setText("$wt")
            }
            else
            {
                Toast.makeText(this@BMICalc,"Name doesn't exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this@BMICalc, "Failed", Toast.LENGTH_SHORT).show()
        }
        calcBMI.setOnClickListener {
            var h:Double=ht.toDouble()
            h=h*0.01
            var w:Double=wt.toDouble()
            var bmi = w/(h*h)
            //val roundedValue:Double = String.format("%.2f", bmi).toDouble()
            var res:String="$bmi"
            res=res.substring(0,5)
            bmiValue.setText(res)
            if (bmi >= 25.00)
                {
                 bmiCategory.setText("Overweight")
                 conc.text = "You need to exercise more"
                }
            else
                {
                    bmiCategory.setText("Normal")
                    conc.text = "Great!Keep Going"
                }
            }
        }
    }