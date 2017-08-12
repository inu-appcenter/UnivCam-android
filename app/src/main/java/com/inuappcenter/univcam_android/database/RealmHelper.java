package com.inuappcenter.univcam_android.database;

import com.inuappcenter.univcam_android.entites.Album;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

/**
 * Created by ichaeeun on 2017. 8. 11..
 */

public class RealmHelper {

    Realm realm;
    RealmResults<Album> albums;
    Boolean saved;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //SAVE
    public Boolean saveAlbum(final Album album) {
        if (album == null) {
            saved = false;
        } else {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    try {
                        Album a = realm.copyToRealm(album);
                        saved=true;
                    } catch (RealmException e) {
                        e.printStackTrace();
                        saved = false;
                    }
                }
            });
        }
        return saved;
    }

    public void retrieveFromDB() {
        albums = realm.where(Album.class).findAll();
    }

    public ArrayList<Album> justRefresh() {
        ArrayList<Album> lates = new ArrayList<>();
        for (Album album : albums) {
            lates.add(album);
        }
        return lates;
    }

}
