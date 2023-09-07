package com.tienuu.ioscameracapturebutton

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.otaliastudios.cameraview.CameraView
import com.tienuu.lib.camera.ioscapturebutton.CaptureVideoButton

class MainActivity : AppCompatActivity() {

    private lateinit var cameraView: CameraView
    private lateinit var btnCaptureVideo: CaptureVideoButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWithPermission()
    }

    private fun initView() {
        cameraView = findViewById(R.id.cameraView)
        btnCaptureVideo = findViewById(R.id.btnCaptureVideo)
        openCamera()
    }

    private fun initWithPermission() {
        if (isHasCameraPermission()) {
            initView()
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 1)
        }
    }

    private fun isHasCameraPermission() =
        checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        initWithPermission()
    }


    private fun openCamera() {
        if (isHasCameraPermission() && !cameraView.isOpened)
            cameraView.open()
    }

    override fun onResume() {
        super.onResume()
        openCamera()
    }

    override fun onPause() {
        super.onPause()
        cameraView.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraView.destroy()
    }
}