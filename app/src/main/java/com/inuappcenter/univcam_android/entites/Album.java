package com.inuappcenter.univcam_android.entites;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by ichaeeun on 2017. 8. 11..
 */

public class Album extends RealmObject {

    @PrimaryKey
    private String albumName;

    private Date albumDate;
    private String albumPath;
    private String thumbnailUri;
    private int quantity;

    @Required
    private Boolean isFavorite;
    private RealmList<Picture> pictures;




    public Album() {

    }

    public Album(String albumName, Date albumDate, String albumPath, String thumbnailUri, int quantity, Boolean isFavorite, RealmList<Picture> pictures) {
        this.albumName = albumName;
        this.albumDate = albumDate;
        this.albumPath = albumPath;
        this.thumbnailUri = thumbnailUri;
        this.quantity = quantity;
        this.isFavorite = isFavorite;
        this.pictures = pictures;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Date getAlbumDate() {
        return albumDate;
    }

    public void setAlbumDate(Date albumDate) {
        this.albumDate = albumDate;
    }

    public String getAlbumPath() {
        return albumPath;
    }

    public void setAlbumPath(String albumPath) {
        this.albumPath = albumPath;
    }

    public String getThumbnailUri() {
        return thumbnailUri;
    }

    public void setThumbnailUri(String thumbnailUri) {
        this.thumbnailUri = thumbnailUri;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public RealmList<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(RealmList<Picture> pictures) {
        this.pictures = pictures;
    }
}
