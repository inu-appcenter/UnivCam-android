package com.inuappcenter.univcam_android.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import com.inuappcenter.univcam_android.activities.AuthRejectActivity
import com.inuappcenter.univcam_android.activities.BaseFragmentActivity
import com.inuappcenter.univcam_android.utils.MarshMellowPermissions


/**
 * Created by ichaeeun on 2017. 8. 6..
 */

abstract class BaseFragment : Fragment() {

//    private lateinit var mMarshMellowPermission: MarshMellowPermissions
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        mMarshMellowPermission = MarshMellowPermissions(activity as BaseFragmentActivity)
//
//        if (!mMarshMellowPermission.checkPermissionForCamera()) {
//            startAuthRejectActivity()
//        } else if (!mMarshMellowPermission.checkPermissionForWriteExternalStorage()) {
//            startAuthRejectActivity()
//        } else if (!mMarshMellowPermission.checkPermissionForReadExternalStorage()) {
//            startAuthRejectActivity()
//        }
//
//    }
//
//    fun startAuthRejectActivity() {
//        Intent(activity, AuthRejectActivity::class.java).let{
//            startActivity(it)
//        }
//    }

}