package com.inuappcenter.univcam_android.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import com.inuappcenter.univcam_android.fragments.AlbumSelectFragment

class AlbumSelectActivity : BaseFragmentActivity() {
    override fun createFragment(): Fragment {
        return AlbumSelectFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
