package com.carlos.ec2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carlos.ec2.databinding.ActivityMapaBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapaBinding
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentMap =
            supportFragmentManager.findFragmentById(R.id.fcvmap) as SupportMapFragment
        fragmentMap.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val idatCentroLocation = LatLng(-12.066877, -77.035721)
        googleMap.addMarker(MarkerOptions().position(idatCentroLocation).title("Idat Centro"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(idatCentroLocation, 16f))

        val tambo1Location = LatLng(-12.104266, -76.968261)
        googleMap.addMarker(MarkerOptions().position(tambo1Location).title("Tambo 1"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tambo1Location, 16f))

        val tambo2Location = LatLng(-12.061893, -76.976038)
        googleMap.addMarker(MarkerOptions().position(tambo2Location).title("Tambo 2"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tambo2Location, 16f))

        val tambo3Location = LatLng(-12.062390, -76.947893)
        googleMap.addMarker(MarkerOptions().position(tambo3Location).title("Tambo 3"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tambo3Location, 16f))
    }
}