package com.inuappcenter.univcam_android.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.inuappcenter.univcam_android.R;
import com.inuappcenter.univcam_android.database.RealmHelper;

import io.realm.Realm;


/**
 * Created by ichaeeun on 2017. 7. 24..
 */

public class AlbumDialogFragment extends android.support.v4.app.DialogFragment {

    EditText albumTitleEt;
    TextView okButton;
    ImageButton cancelButton;
    AlbumDialogInterface mAlbumDialogInterface;
    Realm mRealm;
    RealmHelper mRealmHelper;
    TextView cannot_add_album;

    public static AlbumDialogFragment newInstance(AlbumDialogInterface albumDialogInterface) {
        AlbumDialogFragment fragment = new AlbumDialogFragment();
        fragment.mAlbumDialogInterface = albumDialogInterface;
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_album, container);

        Realm.init(getContext());
        mRealm = Realm.getDefaultInstance();

        mRealmHelper = new RealmHelper(mRealm);
//        mRealmHelper.updateAlbumSorted();


        albumTitleEt = view.findViewById(R.id.album_title_et);
        okButton = view.findViewById(R.id.ok_button);
        cancelButton = view.findViewById(R.id.cancel_button);
        albumTitleEt.requestFocus();
        cannot_add_album = view.findViewById(R.id.cannot_add_album);

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

        albumTitleEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    // input창에 문자를 입력할때마다 호출된다.
                    // search 메소드를 호출한다.
                    String text = albumTitleEt.getText().toString();
                    boolean hasAlbum = mRealmHelper.hasAlbum(text);
                    if (text.length() == 0) {
                        okButton.setEnabled(false);
                        okButton.setTextColor(getResources().getColor(R.color.text_hint));
                        cannot_add_album.setVisibility(View.GONE);
                    } else if (text.length()>0) {
                        if (!hasAlbum) {
                            cannot_add_album.setVisibility(View.GONE);
                            okButton.setEnabled(true);
                            okButton.setTextColor(getResources().getColor(R.color.text_primary));
                        } else {
                            cannot_add_album.setVisibility(View.VISIBLE);
                            okButton.setEnabled(false);
                            okButton.setTextColor(getResources().getColor(R.color.text_hint));
                        }
                    }
                }
            });

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
