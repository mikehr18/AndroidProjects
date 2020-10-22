package com.example.whereami

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener{val intent = Intent(this,login::class.java)
            startActivity(intent)}

        btnRegistro.setOnClickListener{val intent = Intent(this,registro::class.java)
            startActivity(intent)}

        btnAcercade.setOnClickListener{val intent = Intent(this,Acercade::class.java)
            startActivity(intent)}

    }
}
