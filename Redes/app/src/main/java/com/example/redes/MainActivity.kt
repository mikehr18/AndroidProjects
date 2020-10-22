package com.example.redes

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        boton.setOnClickListener{
            var receiver = CustomReceiver()
            val filter = IntentFilter("android.net.wifi.STATE_CHANGE")
            registerReceiver(receiver,filter)
            val intent = Intent("com.workingdev.DOSOMETHING")
            sendBroadcast(intent)
        }
    }


    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }

    inner class  CustomReceiver : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {

            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnected == true

            if (isConnected){

                Toast.makeText(context,"Dispositivo conectado a una red: "+ activeNetwork?.typeName , Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"Conexión perdida",Toast.LENGTH_SHORT).show()
            }
        }

    }


}