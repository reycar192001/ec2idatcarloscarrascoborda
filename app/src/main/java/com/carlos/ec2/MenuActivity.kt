package com.carlos.ec2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.carlos.ec2.databinding.ActivityMainBinding
import com.carlos.ec2.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()



        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.camara -> replaceFragment(CamaraFragment())
                R.id.mapa -> replaceFragment(MapaFragment())
                R.id.informacion -> replaceFragment(InfoFragment())
                else -> {
                    // Opción no reconocida
                }
            }
            true
        }
        binding.buttonMapa.setOnClickListener{
            val intent = Intent(this,MapaActivity::class.java)
            startActivity(intent)

        }
    }

    private fun openGoogleMaps(){
        val intentUri = Uri.parse("geo:0,0?q=-12.066877,-77.035721(IDAT-Lima centro)")
        val mapIntent=Intent(Intent.ACTION_VIEW,intentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(packageManager).let {
            startActivity(mapIntent)
        }
    }

    private fun setupViews() {
        binding.buttonCamara.setOnClickListener {
            val intent= Intent(this,CamaraActivity::class.java)
            startActivity(intent)
        }
        binding.buttonMapa.setOnClickListener {
            val intent= Intent(this,MapaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}