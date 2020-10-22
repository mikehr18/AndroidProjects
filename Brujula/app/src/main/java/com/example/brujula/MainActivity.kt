package com.example.brujula

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),SensorEventListener {

    private var deviceSensors: List<Sensor> = emptyList()
    private var sensor: Sensor?= null
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensor= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if(sensor == null)
            println("TYPE_Accelerometro No SOPORTADO")

        deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
        deviceSensors.forEach {
            println(it)

        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER){
            var x =event.values[0]
            var y =event.values[1]
            var z =event.values[2]

            println("X:" + x + "Y: "+y+"Z: "+z)


            if(z>1){arrow.scaleY = 1-(z/9.8f)}
            if(z< -1){
                arrow.rotation = -180f * (y/9.8f)
                arrow.scaleY = 1+(z/9.8f)
            }
            if(y>1 && x<0){arrow.rotation = 180f * (y/9.8f)}
            else if(y<0 && x>=0){arrow.rotation = 0f *(y/9.8f)}
            else if(x>1 && y>=0 && z>=0){arrow.rotation = 270f *(x/9.8f)}
            else if(x<0 && y<0){arrow.rotation = 270f *(x/9.8f)}
            else if(x>0 && y<1 && z>=0){arrow.rotation = 290f *(x/9.8f)}
        }}

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
    }
}