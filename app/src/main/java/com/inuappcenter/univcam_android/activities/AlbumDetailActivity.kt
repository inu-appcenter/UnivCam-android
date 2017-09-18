package com.inuappcenter.univcam_android.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.inuappcenter.univcam_android.fragments.AlbumDetailFragment

class AlbumDetailActivity : BaseFragmentActivity() {
    override fun createFragment(): Fragment {
        return AlbumDetailFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        if ((fragment as AlbumDetailFragment).isInActionMode) {
            (fragment as AlbumDetailFragment).isInActionMode = false;
            (fragment as AlbumDetailFragment).mAlbumViewAdapter.albumDetailPictureAdapter.notifyDataSetChanged()
            Log.d("AlbumDetailActivity", "isInActionMode = ${(fragment as AlbumDetailFragment).isInActionMode}")
        } else {
            Log.d("AlbumDetailActivity", "fragment as AlbumDetailFragment).isInActionMode = false, isInActionMode = ${(fragment as AlbumDetailFragment).isInActionMode}")
            super.onBackPressed()
        }

    }
}
