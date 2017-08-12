package com.inuappcenter.univcam_android.utils

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.inuappcenter.univcam_android.activities.BaseFragmentActivity
import com.inuappcenter.univcam_android.dialogs.AuthRejectDialogFragment


/**
 * Created by ichaeeun on 2017. 8. 6..
 */

class MarshMellowPermissions(var mActivity: BaseFragmentActivity) {

    private val EXTERNAL_STORAGE_WRITE_PERMISSION_REQUEST_CODE = 10

    private val EXTERNAL_STORAGE_READ_PERMISSION_REQUEST_CODE = 11

    private val CAMERA_PERMISSION_REQUEST_CODE = 12


    fun checkPermissionForReadExternalStorage(): Boolean {
        val result = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    fun checkPermissionForWriteExternalStorage(): Boolean {
        val result = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    fun checkPermissionForCamera(): Boolean {
        val result = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA)
        return result == PackageManager.PERMISSION_GRANTED
    }


    fun requestPermission(fragmentManager: FragmentManager) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.CAMERA)) {
            // Custom 다이얼로그 띄우기
            val ft = fragmentManager.beginTransaction()
            val authRejectAlbumDialog = AuthRejectDialogFragment.newInstance()
//            authRejectAlbumDialog.activity.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //그림자 없애기
            authRejectAlbumDialog.show(ft, "authDialog")

        } else {
            ActivityCompat.requestPermissions(mActivity, arrayOf<String>(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), EXTERNAL_STORAGE_READ_PERMISSION_REQUEST_CODE)
        }
    }



//
//    fun requestPermissionForWriteExternalStorage() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            Toast.makeText(mActivity, " Write Storage permission is needed. Please turn it on inside the settings", Toast.LENGTH_SHORT).show()
//        } else {
//            ActivityCompat.requestPermissions(mActivity, arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE), EXTERNAL_STORAGE_WRITE_PERMISSION_REQUEST_CODE)
//        }
//    }
//
//    fun requestPermissionForCamera() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.CAMERA)) {
//            Toast.makeText(mActivity, " Camera permission is needed. Please turn it on inside the settings", Toast.LENGTH_SHORT).show()
//        } else {
//            ActivityCompat.requestPermissions(mActivity, arrayOf<String>(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
//        }
//    }

}