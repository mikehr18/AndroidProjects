package com.example.whereami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.location.Location
import android.net.Uri
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson




class home : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var lastLocation: android.location.Location

    companion object{
        public const val LOCATION_PERMISSION_REQUEST_CODE = 1

    }


    override fun onMarkerClick(p0: Marker?) = false

    private lateinit var mMap: GoogleMap

    inner class Location{
        var hour:String=""
        var latitude:String=""
        var longitude:String=""
    }

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


        btnMap.setOnClickListener {
            var rq: RequestQueue = Volley.newRequestQueue(this)
            var sr=object: StringRequest(
                Request.Method.POST,Appinfo.web+"location/getLocation",
                Response.Listener { response ->

                    println(response)
                    var g= Gson()
                    var respuesta=g.fromJson(response,Location().javaClass)
                    println(respuesta.hour)
                    println(respuesta.latitude)
                    println(respuesta.longitude)


                    if (respuesta.latitude!="" && respuesta.longitude!=""){

                        val gmmIntentUri = Uri.parse("geo:"+respuesta.latitude+","+respuesta.longitude)
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        if (mapIntent.resolveActivity(packageManager) != null) {
                            startActivity(mapIntent)
                        }
                    }
                    else Toast.makeText(this,"Login Failed!", Toast.LENGTH_LONG).show()
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()

                })
            {

            }

            rq.add(sr)








        }

    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        // val sydney = LatLng(-34.0, 151.0)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.setOnMarkerClickListener(this)
        mMap.uiSettings.isZoomControlsEnabled = true
        setUpMap()
    }

    private fun placeMarker(location: LatLng){

        val markerOption = MarkerOptions().position(location).title("You are here!").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        mMap.addMarker(markerOption)
    }

    private fun setUpMap(){

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                MapsActivity.LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        
        mMap.isMyLocationEnabled = true

        //Tipos de mapas
        mMap.mapType=GoogleMap.MAP_TYPE_NORMAL
        //mMap.mapType=GoogleMap.MAP_TYPE_SATELLITE

        fusedLocationProviderClient.lastLocation.addOnSuccessListener (this){ location ->

            if(location != null){
                lastLocation=location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarker(currentLatLng)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,15f))

            }
        }


    }
}
