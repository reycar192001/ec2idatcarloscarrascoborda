package com.carlos.ec2

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.carlos.ec2.databinding.ActivityCamaraBinding

class CamaraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCamaraBinding
    private lateinit var openCameraLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCamaraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddPhoto.setOnClickListener {
            if (permissionValidated()) {
                openCamera()
            }

        }
        openCameraLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == RESULT_OK){
                val photo : Bitmap = result.data?.extras?.get("data") as Bitmap

                binding.imgPhoto.setImageBitmap(photo)

            }
        }
    }

    private fun permissionValidated(): Boolean {

        val cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        val permissionList: MutableList<String> = mutableListOf()
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(android.Manifest.permission.CAMERA)
        }
        if (permissionList.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toTypedArray(), 1000)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1000 -> {
                if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    openCamera()
                }else{
                    Toast.makeText(this, "Permiso de camara denegado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun openCamera() {
        val intent = Intent()
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE)
        openCameraLauncher.launch(intent)
    }
}