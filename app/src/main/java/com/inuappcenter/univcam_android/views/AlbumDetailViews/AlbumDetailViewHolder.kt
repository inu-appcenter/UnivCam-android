package com.inuappcenter.univcam_android.views.AlbumDetailViews

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.album_detail_item.view.*

/**
 * Created by ichaeeun on 2017. 8. 13..
 */

class AlbumDetailViewHolder(view : View): RecyclerView.ViewHolder(view){
    val headerTitle: TextView = itemView.albumName
    val pictureRecyclerview: RecyclerView = itemView.recyclerview

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


}