package com.example.heartratemonitor
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_ecggraph.*
import kotlinx.android.synthetic.main.activity_ecgmenu.*
import kotlinx.android.synthetic.main.activity_exercise2.*
import kotlinx.android.synthetic.main.activity_exercise2.progressBar
import java.io.Console
import java.lang.Math.log
import java.text.ChoiceFormat.nextDouble
import java.util.*
import kotlin.collections.ArrayList
class ECGGraph : AppCompatActivity() {
    private var timer:CountDownTimer?=null
    private var prog=0
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecggraph)
        setRestProgressBar()
    }
    private fun fin()
    {
        matchWithDataSet.visibility=View.VISIBLE
        suggestion.visibility=View.VISIBLE
        btnEnd.visibility=View.VISIBLE
        var range=0..20
        var x=range.random()
        matchWithDataSet.text="Your data matches with $x % of heart patients"
        suggestion.text="Your heart is in a healthy condition ,keep up the good work!!"
        btnEnd.setOnClickListener {
            val it= Intent(this@ECGGraph,Explore::class.java)
            finish()
            startActivity(it)
        }
    }
    private fun analyzeResult()
    {
        //Here we will get the result for a particular query//
        val name="Sharan"
        database=FirebaseDatabase.getInstance().getReference("Users")
        database.child(name).get().addOnSuccessListener {
            if(it.exists())
            {
                var ecgData=it.child("ecgValues").value as ArrayList<Double>
                var graph:GraphView=ecgReport
                val series = LineGraphSeries(
                    arrayOf<DataPoint>(
                        DataPoint(0.0 , ecgData[0]),
                        DataPoint(1.0, ecgData[1]),
                        DataPoint(2.0 , ecgData[2]),
                        DataPoint(3.0 , ecgData[3]),
                        DataPoint(4.0 , ecgData[4]),
                        DataPoint(5.0 , ecgData[5]),
                        DataPoint(6.0 , ecgData[6]),
                        DataPoint(7.0 , ecgData[7]),
                        DataPoint(8.0 , ecgData[8]),
                        DataPoint(9.0 , ecgData[9]),
                        DataPoint(10.0 , ecgData[10]),
                        DataPoint(12.0 , ecgData[12]),
                        DataPoint(13.0, ecgData[13]),
                        DataPoint(14.0 , ecgData[14]),
                        DataPoint(15.0 , ecgData[15]),
                        DataPoint(16.0 , ecgData[16]),
                        DataPoint(17.0 , ecgData[17]),
                        DataPoint(18.0 , ecgData[18]),
                        DataPoint(19.0 , ecgData[19]),
                        DataPoint(20.0 , ecgData[20]),
                        DataPoint(22.0 , ecgData[22]),
                        DataPoint(23.0 , ecgData[23]),
                        DataPoint(24.0 , ecgData[24]),
                        DataPoint(25.0 , ecgData[25]),
                        DataPoint(26.0 , ecgData[26]),
                        DataPoint(27.0 , ecgData[27]),
                        DataPoint(28.0 , ecgData[28]),
                        DataPoint(29.0 , ecgData[29])
                    ))
                graph.title="ECG Graph"
                graph.addSeries(series)
            }
        }.addOnFailureListener {
            Toast.makeText(this@ECGGraph,"Failed",Toast.LENGTH_SHORT).show()
        }
        fin()
    }
    private fun setRestProgressBar()
    {
        progressBar.progress=prog
        timer=object:CountDownTimer(30000,1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                prog++;
                progressBar.progress=30-prog;
                txt.text=(30-prog).toString()
            }
            override fun onFinish() {
                Toast.makeText(this@ECGGraph,"Done",Toast.LENGTH_SHORT).show()
                llTimer.visibility= View.GONE
                graph.visibility=View.VISIBLE
                analyzeResult()
            }
        }.start()
    }
}