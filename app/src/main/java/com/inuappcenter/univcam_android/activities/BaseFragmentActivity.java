package com.inuappcenter.univcam_android.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.inuappcenter.univcam_android.R;
import com.inuappcenter.univcam_android.fragments.AuthRejectFragment;
import com.inuappcenter.univcam_android.utils.MarshMellowPermissions;

/**
 * Created by ichaeeun on 2017. 8. 9..
 */

public abstract class BaseFragmentActivity extends AppCompatActivity {

    private MarshMellowPermissions mMarshMellowPermission;

    abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_base);

        mMarshMellowPermission = new MarshMellowPermissions(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_fragment_base_fragmentContainer);


        if (!mMarshMellowPermission.checkPermissionForCamera() || !mMarshMellowPermission.checkPermissionForWriteExternalStorage() || !mMarshMellowPermission.checkPermissionForReadExternalStorage()){
            if (fragment == null){
                fragment = new AuthRejectFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.activity_fragment_base_fragmentContainer,fragment)
                        .commit();
            }

        } else{
            if (fragment == null){
                fragment = createFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.activity_fragment_base_fragmentContainer,fragment)
                        .commit();
            }
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_fragment_base_fragmentContainer);


        if (!mMarshMellowPermission.checkPermissionForCamera() || !mMarshMellowPermission.checkPermissionForWriteExternalStorage() || !mMarshMellowPermission.checkPermissionForReadExternalStorage()){
            if (fragment !=null){
                fragment = new AuthRejectFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.activity_fragment_base_fragmentContainer,fragment)
                        .commit();
            }

        } else{
            if (fragment !=null){
                fragment = createFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.activity_fragment_base_fragmentContainer,fragment)
                        .commit();
            }
        }

    }
}
