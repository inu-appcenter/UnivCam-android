package com.inuappcenter.univcam_android.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.inuappcenter.univcam_android.R;


/**
 * Created by ichaeeun on 2017. 7. 24..
 */

public class AlbumDialogFragment extends android.support.v4.app.DialogFragment {

    Fragment mContext;
    EditText albumTitleEt;
    TextView okButton;
    ImageButton cancelButton;
    AlbumDialogInterface mAlbumDialogInterface;

    public static AlbumDialogFragment newInstance(AlbumDialogInterface albumDialogInterface) {
        AlbumDialogFragment fragment = new AlbumDialogFragment();
        fragment.mAlbumDialogInterface = albumDialogInterface;
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_album, container);

        albumTitleEt = view.findViewById(R.id.album_title_et);
        okButton = view.findViewById(R.id.ok_button);
        cancelButton = view.findViewById(R.id.cancel_button);
        albumTitleEt.requestFocus();

        //키보드 자동으로 올라오기
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                albumTitleEt.clearFocus();
                albumTitleEt.requestFocus();
                InputMethodManager mgr = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);


                mgr.showSoftInput(albumTitleEt, InputMethodManager.SHOW_IMPLICIT);

            }
        }, 100);


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlbumDialogInterface.createAlbum((albumTitleEt.getText().toString()));
                dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    return view;

    }
    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }
}
