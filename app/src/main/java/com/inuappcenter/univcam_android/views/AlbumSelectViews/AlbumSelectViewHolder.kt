package com.inuappcenter.univcam_android.views.AlbumViews

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.inuappcenter.univcam_android.entites.ItemClickListener
import kotlinx.android.synthetic.main.album_select_item.view.*
import kotlinx.android.synthetic.main.album_select_item.view.*

/**
 * Created by ichaeeun on 2017. 7. 29..
 */

class AlbumSelectViewHolder(view :View): RecyclerView.ViewHolder(view) {


    var select_layout: LinearLayout = itemView.select_layout
    val tv_title: TextView = itemView.title
    val tv_quantity: TextView = itemView.quantity
    val thumbnail: ImageView = itemView.thumbnail
    val checkbox: CheckBox = itemView.checkbox
    var mItemClickListener: ItemClickListener? = null


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