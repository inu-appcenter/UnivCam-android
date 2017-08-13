package com.inuappcenter.univcam_android.entites;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ichaeeun on 2017. 8. 11..
 */

public class AlbumSelected {

    String title;

    String thumbnailUri;
    int quantity;
    boolean isFavorite;

    public AlbumSelected() {

    }

    public AlbumSelected(String title, String thumbnailUri, int quantity, boolean isFavorite) {
        this.title = title;
        this.thumbnailUri = thumbnailUri;
        this.quantity = quantity;
        this.isFavorite = isFavorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


}
