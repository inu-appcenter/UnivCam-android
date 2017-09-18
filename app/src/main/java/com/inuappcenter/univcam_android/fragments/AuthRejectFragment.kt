package com.inuappcenter.univcam_android.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.inuappcenter.univcam_android.R
import kotlinx.android.synthetic.main.fragment_auth_reject.*
import com.inuappcenter.univcam_android.activities.BaseFragmentActivity
import com.inuappcenter.univcam_android.utils.MarshMellowPermissions


/**
 * A simple [Fragment] subclass.
 */
class AuthRejectFragment : Fragment() {


    private lateinit var mMarshMellowPermission: MarshMellowPermissions

    companion object {
        fun newInstance(): AuthRejectFragment {
            return AuthRejectFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_auth_reject, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        request_permission_button.setOnClickListener {
            //TODO: 시스템 권한 다이얼로그 띄우기

            mMarshMellowPermission = MarshMellowPermissions(activity as BaseFragmentActivity)

            // Custom 다이얼로그 띄우기
//            val ft = fragmentManager.beginTransaction()
//            val authRejectAlbumDialog = AuthRejectDialogFragment.newInstance()
////            authRejectAlbumDialog.activity.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //그림자 없애기
//            authRejectAlbumDialog.show(ft, "authDialog")
            val permCamera = mMarshMellowPermission.checkPermissionForCamera()
            val permRead = mMarshMellowPermission.checkPermissionForWriteExternalStorage()
            val permWrite = mMarshMellowPermission.checkPermissionForReadExternalStorage()

            Log.v("TAG", "permCamera : $permCamera   permRead : $permRead   permWrite : $permWrite")

            mMarshMellowPermission.requestPermission(fragmentManager)


        }
    }

    override fun onResume() {
        super.onResume()
        request_permission_button.setOnClickListener {
            //TODO: 시스템 권한 다이얼로그 띄우기

            mMarshMellowPermission = MarshMellowPermissions(activity as BaseFragmentActivity)

            // Custom 다이얼로그 띄우기
//            val ft = fragmentManager.beginTransaction()
//            val authRejectAlbumDialog = AuthRejectDialogFragment.newInstance()
////            authRejectAlbumDialog.activity.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //그림자 없애기
//            authRejectAlbumDialog.show(ft, "authDialog")

            mMarshMellowPermission.requestPermission(fragmentManager)


        }
    }

}// Required empty public constructor
