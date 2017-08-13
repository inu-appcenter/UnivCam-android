package com.inuappcenter.univcam_android.views.AlbumDetailViews

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RelativeLayout
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.fragments.AlbumDetailFragment
import kotlinx.android.synthetic.main.album_detail_picture_item.view.*

/**
 * Created by ichaeeun on 2017. 8. 13..
 */

class AlbumDetailPictureViewHolder(var view :View, var selectfragment: AlbumDetailFragment): RecyclerView.ViewHolder(view), View.OnClickListener {


    var a: Boolean =  true

    val checkbox: CheckBox = itemView.checkbox
    val select_layout: RelativeLayout = itemView.picture_layout
    var picture: ImageView = itemView.picture

    init {
        select_layout.setOnClickListener(this)
        checkbox.setOnClickListener(this)

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


    override fun onClick(p0: View) {
//        selectfragment.prepareSelection(p0, adapterPosition)
        if(a) {
            select_layout.setBackgroundResource(R.drawable.univ_select_stroke)

            checkbox.isChecked =true
//            val alpha = thumbnail.drawable
//            alpha.alpha = 210
            a = false
        }else {
            checkbox.isChecked = false
            select_layout.setBackgroundResource(R.drawable.univ_stroke)
//            val alpha = thumbnail.drawable
//            alpha.alpha = 255
            a = true
        }
    }
}