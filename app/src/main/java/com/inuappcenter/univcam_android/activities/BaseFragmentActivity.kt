//package com.inuappcenter.univcam_android.activities
//
//import android.os.Bundle
//import android.support.v4.app.Fragment
//import android.support.v7.app.AppCompatActivity
//import android.util.Log
//import com.inuappcenter.univcam_android.R
//
//
//abstract class BaseFragmentActivity : AppCompatActivity() {
//
//
//    override fun onCreate(savedInstanceState: Bundle?){
//        setTheme(R.style.AppTheme)
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_fragment_base)
//
//        var fragment = supportFragmentManager.findFragmentById(R.id.activity_fragment_base_fragmentContainer)
//        Log.d("BaseFragmentActivity", fragment.toString())
//
//        if (fragment == null) {
//            fragment = createFragment()
//            supportFragmentManager.beginTransaction()
//                    .add(R.id.activity_fragment_base_fragmentContainer, fragment)
//                    .commit()
//        }
//    }
//
//
//
//
//    abstract fun createFragment(): Fragment
//
//
//
//
//}
