package com.example.sqlite

import Database
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_delete.*

class delete : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val database = Database(this)

        btnDelete.setOnClickListener {
            val db = database.writableDatabase
            val where = "user = ?"
            val whereParams = arrayOf(userDelete.text.toString())
            val deletedRows = db.delete("users",
                where,
                whereParams)
            println("SE BORRO: "+deletedRows)

            if (deletedRows!=0) {
                Toast.makeText(this, "Usuario Eliminado", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Ese usuario no existe", Toast.LENGTH_LONG).show()

            }

        }


    }
}
