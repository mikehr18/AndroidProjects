package com.example.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import  android.content.Context
import  android.content.SharedPreferences
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, second::class.java)
            startActivity(intent)
        }
    }
    override fun onPause() {
        super.onPause()
        saveData()
    }
    private fun saveData() {
        val filename = "$packageName TESTFILE"
        val pref = getSharedPreferences(filename, Context.MODE_PRIVATE)
        val edit = pref.edit()
        edit.putString("lastname", txtlastname.text.toString())
        edit.putString("firstname", txtfirstname.text.toString())
        edit.apply()
        Toast.makeText(this, "Saved data", Toast.LENGTH_LONG).show()
    }
    override fun onResume() {
        super.onResume()
        txtfirstname.setText("")
        txtlastname.setText("")
        txtfirstname.setHint("first name")
        txtlastname.setHint("last name")
    }
}