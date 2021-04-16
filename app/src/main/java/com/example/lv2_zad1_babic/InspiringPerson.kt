package com.example.lv2_zad1_babic

import android.graphics.Bitmap

class InspiringPerson {

    var id:Int?=null

    var firstName:String?=null

    var lastName:String?=null

    var description:String?=null

    var dateOfBirth:String?=null

    var dateOfDeath:String?=null

    var image: Bitmap?=null

    var quotes: MutableList<String> = mutableListOf<String>()

}