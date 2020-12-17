package com.mehroz.permutil

interface PermissionCallback {
        fun onPermissionGranted()

        fun onIndividualPermissionGranted(grantedPermission: Array<String>)

        fun onPermissionDenied(deniedPermission: Array<String>)

        fun onPermissionDeniedBySystem()
    }