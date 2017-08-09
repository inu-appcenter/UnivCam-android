package com.inuappcenter.univcam_android.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.inuappcenter.univcam_android.R


/**
 * Created by ichaeeun on 2017. 8. 8..
 */

class FavoriteFragment : BaseFragment() {


    companion object {
        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_favorite, container, false)
    }





}// Required empty public constructor
