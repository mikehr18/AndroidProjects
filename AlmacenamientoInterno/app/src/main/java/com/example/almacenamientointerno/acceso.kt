package com.example.almacenamientointerno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_acceso.*
import kotlinx.android.synthetic.main.activity_login.*


class acceso : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acceso)

        val objetoIntent: Intent =intent
        var nombre=objetoIntent.getStringExtra("nombre")
        usuarioLogeado.text=nombre

    btnSalir.setOnClickListener{
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)

    }

    }


}


