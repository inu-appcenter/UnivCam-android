package com.inuappcenter.univcam_android.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import com.inuappcenter.univcam_android.fragments.AlbumDetailFragment

class AlbumDetailActivity : BaseFragmentActivity() {
    override fun createFragment(): Fragment {
        return AlbumDetailFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
