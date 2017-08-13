package com.inuappcenter.univcam_android.entites;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ichaeeun on 2017. 8. 13..
 */

public class AlbumDetail implements Comparable<AlbumDetail>{

    String headerTitle;

    ArrayList<Picture> pictureList;
    Date date;

    public AlbumDetail() {

    }


    public AlbumDetail(String headerTitle, ArrayList<Picture> pictureList, Date date) {
        this.headerTitle = headerTitle;
        this.pictureList = pictureList;
        this.date = date;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(ArrayList<Picture> pictureList) {
        this.pictureList = pictureList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(@NonNull AlbumDetail albumDetail) {
        if (getDate() == null || albumDetail.getDate() == null)
            return 0;
        return getDate().compareTo(albumDetail.getDate());
    }
}
