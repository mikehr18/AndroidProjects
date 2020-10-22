package com.example.almacenamientointerno

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro.*

class registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        val filename = "ourfile.txt"
        btnRegistrar.setOnClickListener{

            val input = openFileInput(filename)
            var buffer = StringBuilder()
            input.use {

                var bytes_read = input.read()
                while (bytes_read != -1 ) {

                    buffer.append(bytes_read.toChar())
                    bytes_read = input.read()
                }

            }


            val out = openFileOutput(filename, Context.MODE_PRIVATE)
            out.use {
                out.write(buffer.toString().toByteArray() +"\n".toByteArray()+
                        usuarioRegistro.text.toString().toByteArray() +
                        "/".toByteArray()
                        + passwordRegistro.text.toString().toByteArray())
            }
            Toast.makeText(this,"Usuario creado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }




    }
}
