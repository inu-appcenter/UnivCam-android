package com.inuappcenter.univcam_android.views.AlbumDetailViews

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.inuappcenter.univcam_android.entites.Picture
import com.inuappcenter.univcam_android.fragments.AlbumDetailFragment
import java.util.*

/**
 * Created by ichaeeun on 2017. 8. 13..
 */

class AlbumDetailPictureAdapter(var fragment: AlbumDetailFragment, var albumList: ArrayList<Picture>) : RecyclerView.Adapter<AlbumDetailPictureViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AlbumDetailPictureViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




    override fun onBindViewHolder(holder: AlbumDetailPictureViewHolder, position: Int) {
        val item = albumList[position]


    }
    override fun getItemCount(): Int {
        return albumList.size
    }




}


