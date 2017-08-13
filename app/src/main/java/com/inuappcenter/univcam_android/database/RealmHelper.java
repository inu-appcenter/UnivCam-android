package com.inuappcenter.univcam_android.database;

import com.inuappcenter.univcam_android.entites.Album;
import com.inuappcenter.univcam_android.entites.AlbumDetail;
import com.inuappcenter.univcam_android.entites.Picture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
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




    // 앨범 저장
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

    // 사진 저장
    public Boolean savePicture(final String albumName, final Picture picture) {
        if (picture == null) {
            saved = false;
        } else {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    try {
                        Picture p = realm.copyToRealm(picture);
                        Album album = realm.where(Album.class).equalTo("albumName",albumName).findFirst();
                        album.getPictures().add(p);
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

    // 즐겨찾는 앨범 업데이트
    public Boolean updateFavoriteAlbum(final String albumName, final boolean isFavorite) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    Album album = realm.where(Album.class).equalTo("albumName",albumName).findFirst();
                    album.setFavorite(isFavorite);
                    saved=true;
                } catch (RealmException e) {
                    e.printStackTrace();
                    saved = false;
                }
            }
        });

        return saved;
    }



    public void retrieveFromDB() {
        albums = realm.where(Album.class).findAll();
    }

    // 전체 앨범 검색
    public ArrayList<Album> justRefresh() {
        ArrayList<Album> lates = new ArrayList<>();
        for (Album album : albums) {
            lates.add(album);
        }
        return lates;
    }

    //
    String headerTitle;

    ArrayList<Picture> pictureList;
    Date date;
    //
    // TODO: 앨범 detail페이지 검색

    public ArrayList<AlbumDetail> retrieveAlbumDetail(final String albumName) {
        ArrayList<AlbumDetail> albumDetails = new ArrayList<>();
        RealmResults<Picture> picturesList = realm.where(Picture.class).distinct("date");

//        Album album = realm.where(Album.class).equalTo("albumName",albumName).findFirst();
//        RealmResults<Album> albums = realm.where(Album.class).equalTo("albumName",albumName).findAll();
//        RealmList<Picture> pictureList = album.getPictures();

        for(Picture picture: picturesList) {
            Date date = picture.getDate();
            String headerTitle = picture.getCategoryName();

            Album album = realm.where(Album.class)
                                                .equalTo("albumName", albumName)
                                                .equalTo("pictures.date", date)
                                                .findFirst();

            RealmList<Picture> picutreList2 = album.getPictures();

            ArrayList<Picture> pictures = new ArrayList<>();
            for(Picture picture2 : picutreList2) {
                pictures.add(picture2);
            }
            AlbumDetail albumDetail = new AlbumDetail(headerTitle,pictures,date);
            albumDetails.add(albumDetail);

        }

        Collections.sort(albumDetails);
        return albumDetails;




    }


    // 즐겨찾는 앨범 검색
    public ArrayList<Album> retrieveFavoriteAlbums() {
        ArrayList<Album> favoriteAlbums = new ArrayList<>();
        RealmResults<Album> albums = realm.where(Album.class).equalTo("isFavorite",true).findAll();
        for (Album album : albums) {
            favoriteAlbums.add(album);
        }
        return favoriteAlbums;
    }


    // TODO: 앨범 정렬, 앨범명 수정, 앨범 삭제, 앨범 이동, 사진 이동, 사진 카테고리 변경



}
