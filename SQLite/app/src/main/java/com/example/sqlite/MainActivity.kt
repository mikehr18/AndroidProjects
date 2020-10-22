package com.example.sqlite
import Database
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        loginBTN.setOnClickListener{val intent = Intent(this,login::class.java)
            startActivity(intent)}

        registrarBTN.setOnClickListener{val intent = Intent(this,registro::class.java)
            startActivity(intent)}

        borrarBTN.setOnClickListener{val intent = Intent(this,delete::class.java)
            startActivity(intent)}

    }
}
