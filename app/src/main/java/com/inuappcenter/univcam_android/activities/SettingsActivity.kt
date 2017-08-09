package com.inuappcenter.univcam_android.activities

import android.support.v4.app.Fragment
import com.inuappcenter.univcam_android.fragments.SettingsFragment


class SettingsActivity : BaseFragmentActivity() {
    override fun createFragment(): Fragment {
        return SettingsFragment.newInstance()
    }
}
