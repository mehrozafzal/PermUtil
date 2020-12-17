package com.mehroz.permission

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.mehroz.permutil.PermissionCallback
import com.mehroz.permutil.PermissionHelper

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var permissionHelper: PermissionHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permissionHelper = PermissionHelper(
            this, arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE
            ),
            100
        )

        permissionHelper!!.request(object : PermissionCallback {
            override fun onPermissionGranted() {
                Log.d(TAG, "onPermissionGranted() called")
            }

            override fun onIndividualPermissionGranted(grantedPermission: Array<String>) {
                Log.d(
                    TAG,
                    "onIndividualPermissionGranted() called with: grantedPermission = [" + TextUtils.join(
                        ",",
                        grantedPermission
                    ) + "]"
                )
            }

            override fun onPermissionDenied(deniedPermission: Array<String>) {
                Log.d(
                    TAG,
                    "DeniedPermission : deniedPermission = [" + TextUtils.join(
                        ",",
                        deniedPermission
                    ) + "]"
                )
            }

            override fun onPermissionDeniedBySystem() {
                Log.d(TAG, "onPermissionDeniedBySystem() called")
            }
        })

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHelper?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}