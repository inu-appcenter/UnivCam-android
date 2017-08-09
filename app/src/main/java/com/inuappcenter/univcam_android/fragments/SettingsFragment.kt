package com.inuappcenter.univcam_android.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.inuappcenter.univcam_android.R


/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {

    companion object {
        fun newInstance(): AlbumFragment {
            return AlbumFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_settings, container, false)
    }

}
