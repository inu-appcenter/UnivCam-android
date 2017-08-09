package com.inuappcenter.univcam_android.activities

import android.support.v4.app.Fragment
import com.inuappcenter.univcam_android.fragments.AuthRejectFragment

class AuthRejectActivity : BaseFragmentActivity() {
    override fun createFragment(): Fragment {
        return AuthRejectFragment.newInstance()
    }
}
