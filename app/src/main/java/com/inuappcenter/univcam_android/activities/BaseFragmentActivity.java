package com.inuappcenter.univcam_android.activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.inuappcenter.univcam_android.R;
import com.inuappcenter.univcam_android.fragments.AuthRejectFragment;
import com.inuappcenter.univcam_android.utils.MarshMellowPermissions;

/**
 * Created by ichaeeun on 2017. 8. 9..
 */

public abstract class BaseFragmentActivity extends AppCompatActivity {

    private MarshMellowPermissions mMarshMellowPermission;

    abstract Fragment createFragment();
    FragmentManager fragmentManager;
    Fragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_base);

        mMarshMellowPermission = new MarshMellowPermissions(this);

        fragmentManager = getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.activity_fragment_base_fragmentContainer);


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



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 11: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 동의 및 로직 처리
                    Log.e("TAG", ">>> 카메라 동의함.");
                    if (mMarshMellowPermission.checkPermissionForCamera() && mMarshMellowPermission.checkPermissionForWriteExternalStorage() && mMarshMellowPermission.checkPermissionForReadExternalStorage()) {
                        if (fragment != null) {
                            fragment = createFragment();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.activity_fragment_base_fragmentContainer, fragment)
                                    .commit();
                        }
                    }


                } else if(grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("TAG", ">>> 저장공간 동의함.");
                } else{
                    // 동의 안함
                    Log.d("da", ">>> 동의를 해주셔야 합니다.");
                }
                return;
            }
            // 예외 케이스
        }
    }


}
