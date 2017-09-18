package com.inuappcenter.univcam_android.activities

import android.support.v4.app.Fragment
import com.inuappcenter.univcam_android.fragments.FavoriteFragment


class FavoriteActivity : BaseFragmentActivity() {
    override fun createFragment(): Fragment {
        return FavoriteFragment.newInstance()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, 0)
        finish()
    }
}
