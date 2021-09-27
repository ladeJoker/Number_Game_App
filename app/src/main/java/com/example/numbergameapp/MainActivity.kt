package com.example.numbergameapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var userGuess: ArrayList<Int>
    lateinit var leftedGuess: TextView
    lateinit var input: EditText
    lateinit var checkGuess: Button
    lateinit var myRV: RecyclerView
    lateinit var appAlert: ConstraintLayout
    var counter: Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userGuess = ArrayList()
        leftedGuess = findViewById(R.id.tvTrails)
        input = findViewById(R.id.etInput)
        checkGuess = findViewById(R.id.btCheck)


        myRV = findViewById(R.id.rvMain)
        appAlert = findViewById(R.id.alert)
        checkGuess.setOnClickListener (){ userEntry() }

    }


    fun perform(randomNum: Int , toCheck: Int){
        if (toCheck == randomNum) {
            leftedGuess.text = "Correct!!"
            userGuess.add(toCheck)
            myRV.adapter = RecyclerViewAdapter(userGuess)
            myRV.layoutManager = LinearLayoutManager(this)
            input.setText("")
        } else {
            userGuess.add(toCheck)
            myRV.adapter = RecyclerViewAdapter(userGuess)
            myRV.layoutManager = LinearLayoutManager(this)
            input.setText("")
            counter--
            leftedGuess.text = "You Have $counter guesses left"
            if(counter==3){
                leftedGuess.text = "Game Over!"
            }
        }
    }

    fun userEntry(){
        var randomNum = 1
        var isCheck = true

        if (counter > 0) {
        var toCheck = input.text.toString()
        try {
            Integer.parseInt(toCheck)
        }catch (e:Exception){
            isCheck = false
        }

            if (isCheck == true) {
                perform(randomNum , toCheck.toInt())
            } else {
                Snackbar.make(appAlert, "Enter a Number", Snackbar.LENGTH_LONG).show()
            }
        }else{
            Snackbar.make(appAlert, "Game Over :(", Snackbar.LENGTH_LONG).show()
        }

    }



}