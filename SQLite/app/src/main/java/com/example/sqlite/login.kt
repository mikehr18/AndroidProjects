package com.example.sqlite

import Database
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val database = Database(this)

        btnLogin.setOnClickListener{
            val db = database.readableDatabase
            val select = arrayOf("user","password")
            val where = "user = ? AND password = ?"
            val whereParams = arrayOf(userLogin.text.toString(),passLogin.text.toString())


            val cursor = db.query(
                "users",
                select,
                where,
                whereParams,
                null,
                null,
                null
            )
            with(cursor){
                if (moveToNext()){
                    println("User: "+getString(getColumnIndexOrThrow("user")))
                    println("Password: "+getString(getColumnIndexOrThrow("password")))
                    Toast.makeText(this@login,"Login correcto", Toast.LENGTH_LONG).show()
                    var nombre:String=userLogin.text.toString()
                    val intent = Intent(this@login,sesion::class.java)
                    intent.putExtra("nombre", nombre)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this@login,"Usuario y/o Password Incorrecto", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@login,MainActivity::class.java)
                    startActivity(intent)
                }




            }

        }


    }
}
