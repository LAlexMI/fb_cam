package com.android.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_camera.button

class CameraActivity: AppCompatActivity() {

    private var camera: Camera? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        button.setOnClickListener {
            if (camera != null) {
                stopCamera()
            } else {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_GRANTED) {
                    startCamera()
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        //permission dialogs are shown for sdk 23 and above
                        requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA && grantResults.isNotEmpty() && grantResults[0] == PackageManager
                .PERMISSION_GRANTED) {
            startCamera()
        }
    }

    private fun startCamera() {
        camera = Camera.open(0)
    }

    private fun stopCamera() {
        camera?.release()
        camera = null
    }

    override fun onStop() {
        super.onStop()
        stopCamera()
    }

    companion object {
        private const val REQUEST_CAMERA = 1001

    }
}