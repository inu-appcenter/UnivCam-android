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

class AlbumDetailPictureViewHolder(var view :View, var fragment: AlbumDetailFragment): RecyclerView.ViewHolder(view), View.OnClickListener {


    var b: Boolean = false
    val checkbox: CheckBox = itemView.checkbox
    val select_layout: RelativeLayout = itemView.picture_layout
    var picture: ImageView = itemView.picture

    init {

        select_layout.setOnLongClickListener{
            if(!checkbox.isChecked) {
                checkbox.isChecked = true
            }
            fragment.fragment_album_detail_toolbar.menu.clear()
            fragment.fragment_album_detail_toolbar.inflateMenu(R.menu.menu_detail_album_action_mode)
            fragment.fragment_album_detail_toolbar.title
            fragment.isInActionMode = true
            fragment.mAlbumViewAdapter.notifyDataSetChanged()
            (fragment.activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
            fragment.fragment_album_detail_toolbar.setNavigationIcon(R.drawable.ic_back_24_dp)
            fragment.fragment_album_detail_toolbar.setNavigationOnClickListener {
            fragment.activity.finish()
        }
            return@setOnLongClickListener true


        }
        checkbox.setOnClickListener(this)
        select_layout.setOnClickListener{
            if (b) {
                checkbox.isChecked = !checkbox.isChecked
                b = true
            } else {
                checkbox.isChecked = !checkbox.isChecked
            }

        }
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
        fragment.prepareSelection(p0, adapterPosition)
    }


}