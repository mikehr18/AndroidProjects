package com.example.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import kotlinx.android.synthetic.main.activity_second.*

class second : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        btnloaddata.setOnClickListener {
            val filename = "$packageName TESTFILE"
            val pref = getSharedPreferences(filename, Context.MODE_PRIVATE)
            val mlastname = pref.getString("lastname", "")
            val mfirstname = pref.getString("firstname", "")
            txtoutput.text = "$mfirstname $mlastname "
        }
    }
    override fun onResume() {
        super.onResume()
        txtoutput.text = "Click 'LOAD DATA'"
    }
}
