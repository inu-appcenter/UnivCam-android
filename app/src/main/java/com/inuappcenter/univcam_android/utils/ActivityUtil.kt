package com.inuappcenter.univcam_android.utils

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by ichaeeun on 2017. 8. 8..
 */

// TODO Fragment replace 함수 생성
// 중위 표기법

fun AppCompatActivity.replaceFragmentToActivity(fragment: Fragment, frameId: Int) {
    val transaction = this.supportFragmentManager.beginTransaction()
    transaction.add(frameId, fragment)
    transaction.commit()
}

