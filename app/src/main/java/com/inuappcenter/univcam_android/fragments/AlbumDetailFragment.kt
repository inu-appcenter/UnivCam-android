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
class AlbumDetailFragment : Fragment() {

    companion object {
        fun newInstance(): AlbumDetailFragment {
            return AlbumDetailFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_album_detail, container, false)
    }

}// Required empty public constructor
