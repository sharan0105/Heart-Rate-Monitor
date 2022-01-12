package com.example.heartratemonitor
import org.json.JSONArray
data class Person(
    val email:String?="x@gmail.com",
    var name:String="me",
    val age:Int=0,
    var gender:String="Male",
//    val email:String="me",
    val weight:String="85",
    val height:String="180",
   var ecgData:ArrayList<Double> = listOf(1.3,2.5,3.6) as ArrayList<Double>,
   var res:Boolean=false
)
