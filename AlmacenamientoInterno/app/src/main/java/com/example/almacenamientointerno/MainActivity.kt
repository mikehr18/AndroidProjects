package com.example.almacenamientointerno

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val filename = "ourfile.txt"




        /*getbutton.setOnClickListener{
            val input = openFileInput(filename)
            input.use {
                var buffer = StringBuilder()
                var bytes_read = input.read()
                while (bytes_read != -1 ){

                    buffer.append(bytes_read.toChar())
                    bytes_read=input.read()
                }
                editText.setText(buffer.toString())

            }

        }*/

        btnSendLogin.setOnClickListener{val intent = Intent(this,login::class.java)
            startActivity(intent)}

        btnSendRegistro.setOnClickListener{val intent = Intent(this,registro::class.java)
            startActivity(intent)}
        btnBorrar.setOnClickListener{
            var buffer = StringBuilder()
            val out = openFileOutput(filename, Context.MODE_PRIVATE)
            out.use {
                out.write(
                    "".toByteArray()
                )
            }
            Toast.makeText(this,"Se han borrado todos los usuarios", Toast.LENGTH_SHORT).show()

        }
    }

}
