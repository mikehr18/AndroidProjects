package com.example.sqlite

import Database
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro.*

class registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val database = Database(this)

        btnSave.setOnClickListener{
            val db = database.writableDatabase


            val values = ContentValues().apply {
                put("user",userRegistro.text.toString())
                put("password",passRegistro.text.toString())

            }
            val newRowId=db.insert("users", null, values)
            println("Creado en index: "+newRowId)
            Toast.makeText(this,"Usuario creado", Toast.LENGTH_LONG).show()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }
}
