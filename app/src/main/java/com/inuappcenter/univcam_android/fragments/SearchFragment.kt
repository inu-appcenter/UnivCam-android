package com.inuappcenter.univcam_android.fragments


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

import com.inuappcenter.univcam_android.R
import kotlinx.android.synthetic.main.fragment_search.*


/**
 * Created by ichaeeun on 2017. 8. 8..
 */

class SearchFragment : BaseFragment() {


    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    var popupdef: WindowManager.LayoutParams? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        activity.setTheme(R.style.AppTheme);
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search)
//        popupdef = WindowManager.LayoutParams()
//
//        // 키보드 자동으로 올라오기
//        val handler = Handler()
//        handler.postDelayed(Runnable {
//            searchEt.clearFocus()
//            searchEt.requestFocus()
//            val mgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//
//
//            mgr.showSoftInput(searchEt, InputMethodManager.SHOW_IMPLICIT)
//        }, 100)

    }
