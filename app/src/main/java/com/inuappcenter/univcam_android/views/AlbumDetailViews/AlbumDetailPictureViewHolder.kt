package com.inuappcenter.univcam_android.views.AlbumDetailViews

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RelativeLayout
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.fragments.AlbumDetailFragment
import kotlinx.android.synthetic.main.album_detail_picture_item.view.*
import kotlinx.android.synthetic.main.fragment_album_detail.*

/**
 * Created by ichaeeun on 2017. 8. 13..
 */

class AlbumDetailPictureViewHolder(var view :View, var fragment: AlbumDetailFragment): RecyclerView.ViewHolder(view) {


    val checkbox: CheckBox = itemView.checkbox
    val select_layout: RelativeLayout = itemView.picture_layout
    var picture: ImageView = itemView.picture
    val layer: View = itemView.layer

    }


    //TODO: Kotlin 더 공부!
//    val tvTitle: TextView by lazy {
//        view.findViewById<TextView>(R.id.title) as TextView
//    }
//
//
//    fun onBindViewHolder(item: Album) {
//        itemView.onViewCreate(item)
//    }
//
//    fun View.onViewCreate(item: Album) {
//        quantity.text = ""
//    }

