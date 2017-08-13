package com.inuappcenter.univcam_android.views.AlbumViews

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.album_item.view.*

/**
 * Created by ichaeeun on 2017. 7. 29..
 */

class FavoriteViewHolder(view :View): RecyclerView.ViewHolder(view){


    var select_layout: LinearLayout = itemView.select_layout
    val tv_title: TextView = itemView.title
    val tv_quantity: TextView = itemView.quantity
    val thumbnail: ImageView = itemView.thumbnail
    val is_favorite: CheckBox = itemView.isFavorite
    val album_menu: ImageButton = itemView.album_menu
    val camera_button : ImageButton = itemView.camera_button


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