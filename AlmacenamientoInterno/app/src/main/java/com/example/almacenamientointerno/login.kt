package com.example.almacenamientointerno

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val filename = "ourfile.txt"

        btnEntrar.setOnClickListener{
            val input = openFileInput(filename)
            input.use {
                var buffer = StringBuilder()
                var bytes_read = input.read()
                while (bytes_read != -1 ){

                    buffer.append(bytes_read.toChar())
                    bytes_read=input.read()
                }
                //editText2.setText(buffer.toString())
                var credenciales= buffer.toString().split("\n")
                var prueba = usuarioLogin.text.toString()+"/"+passwordLogin.text.toString()
                for (i in 0..credenciales.size-1){
                    if (credenciales[i]==prueba){
                        Toast.makeText(this,"ACESSO CORRECTO", Toast.LENGTH_SHORT).show()
                        println(credenciales[i])
                        var nombre:String=usuarioLogin.text.toString()
                        val intent = Intent(this,acceso::class.java)
                        intent.putExtra("nombre", nombre)
                        startActivity(intent)
                    }
                }


            }

        }



    }
}
