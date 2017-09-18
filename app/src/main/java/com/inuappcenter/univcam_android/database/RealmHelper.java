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
import io.realm.Sort;
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




    /** 앨범 저장 **/
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

    /** 사진 저장. **/
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

    /** 사진 갯수 **/
    public int countPictures(final String albumName) {
        Album album = realm.where(Album.class).equalTo("albumName", albumName).findFirst();
        return album.getPictures().size();
    }


    /** 같은 앨범명이 있으면 true, 없으면 false */
    public Boolean hasAlbum(final String albumName) {
        if (albumName == null) {
            return false;
        } else {
            Album album = realm.where(Album.class).equalTo("albumName", albumName).findFirst();
            if (album == null) {
                return false;
            } else {
                return true;
            }
        }
    }

    /** 최신 이미지 가져오기 **/
    public String getLatestPicturePath(final String albumName) {
        Album album = realm.where(Album.class).equalTo("albumName", albumName).findFirst();
        if (album.getPictures().size() == 0) {
            return null;
        } else {
            return album.getPictures().sort("date", Sort.DESCENDING).first().getPicturePath();
        }



    }


    /** 즐겨찾는 앨범 업데이트 **/
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



    /** 앨범 정렬 방법 바꿈 */
    public void updateAlbumSorted(String sort) {
        if (sort.equals("name")) {
            // 이름 순
            albums = realm.where(Album.class).findAllSorted("albumName",Sort.ASCENDING);
        } else if (sort.equals("name_reverse")) {
            // 이름 역순
            albums = realm.where(Album.class).findAllSorted("albumName",Sort.DESCENDING);
        } else if (sort.equals("time")) {
            // 최신 순
            albums = realm.where(Album.class).findAllSorted("albumDate",Sort.DESCENDING);
        } else if (sort.equals("time_reverse")) {
            // 오래된 순
            albums = realm.where(Album.class).findAllSorted("albumDate",Sort.ASCENDING);
        }
    }



    /** 전체 앨범 검색 **/
    public ArrayList<Album> getAlbums() {
        ArrayList<Album> lates = new ArrayList<>();
        for (Album album : albums) {
            lates.add(album);
        }
        return lates;
    }





    /** 사진들 가져오기 **/
    public ArrayList<AlbumDetail> retrieveAlbumDetail(final String albumName) {
        ArrayList<AlbumDetail> albumDetails = new ArrayList<>();

        ArrayList<Picture> pictures;
        Album album2 = realm.where(Album.class).equalTo("albumName", albumName).findFirst();

        if (album2 != null) {
            if (album2.getPictures() != null) {

                RealmResults<Picture> picturesList = album2.getPictures().where().distinct("yearMonthDay")
                        .sort("yearMonthDay", Sort.DESCENDING);

                for(Picture picture: picturesList) {

                    RealmResults<Picture> picture2 = album2.getPictures().where().equalTo("yearMonthDay", picture.getYearMonthDay()).findAllSorted("date",Sort.DESCENDING);
                    pictures = new ArrayList<>(picture2);
                    AlbumDetail albumDetail = new AlbumDetail(pictures.get(0).getCategoryName(), pictures, album2.getAlbumDate());
                    albumDetails.add(albumDetail);
                }

            }
        }


        return albumDetails;
    }


    /** 즐겨찾는 앨범 검색 **/
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
