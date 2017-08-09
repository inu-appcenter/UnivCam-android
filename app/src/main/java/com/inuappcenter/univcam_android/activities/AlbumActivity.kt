package com.inuappcenter.univcam_android.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.fragments.AlbumFragment


class AlbumActivity : BaseFragmentActivity() {
    override fun createFragment(): Fragment {
        return AlbumFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }
}
