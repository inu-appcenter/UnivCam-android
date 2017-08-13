package com.inuappcenter.univcam_android.views.AlbumViews

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.bumptech.glide.load.engine.Resource
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.fragments.AlbumSelectFragment
import kotlinx.android.synthetic.main.album_select_item.view.*
import android.graphics.drawable.Drawable



/**
 * Created by ichaeeun on 2017. 7. 29..
 */

class AlbumSelectViewHolder(var view :View, var selectfragment: AlbumSelectFragment): RecyclerView.ViewHolder(view), View.OnClickListener {


    var a: Boolean =  true

    val select_layout: LinearLayout = itemView.select_layout
    val tv_title: TextView = itemView.title
    val tv_quantity: TextView = itemView.quantity
    val thumbnail: ImageView = itemView.thumbnail
    val checkbox: CheckBox = itemView.checkbox

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