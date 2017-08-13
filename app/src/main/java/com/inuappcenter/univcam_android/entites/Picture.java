package com.inuappcenter.univcam_android.entites;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by ichaeeun on 2017. 8. 13..
 */

public class Picture extends RealmObject {
    @PrimaryKey
    private String picturePath;

    @Required
    private Date date;

    @Required
    private String categoryName;

    public Picture() {

    }

    public Picture(String picturePath, Date date, String categoryName) {
        this.picturePath = picturePath;
        this.date = date;
        this.categoryName = categoryName;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
