package com.inuappcenter.univcam_android.application

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by ichaeeun on 2017. 7. 23..
 */

class ApplicationController : Application() {

    override fun onCreate() {
        super.onCreate()
        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)

//
//        Typekit.getInstance()
//                .addNormal(Typekit.createFromAsset(this, "kopubbatang_medium.otf"))
//                .addBold(Typekit.createFromAsset(this, "kopubbatang_bold.otf"))
    }

    companion object {
        val instance: ApplicationController by lazy {
            ApplicationController()
        }
    }
}
