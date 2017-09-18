package com.inuappcenter.univcam_android.views.AlbumDetailViews

import android.content.Intent
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.bumptech.glide.Glide
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.activities.ImageViewActivity
import com.inuappcenter.univcam_android.entites.Picture
import com.inuappcenter.univcam_android.fragments.AlbumDetailFragment
import kotlinx.android.synthetic.main.fragment_album_detail.*
import java.io.File
import java.util.*

/**
 * Created by ichaeeun on 2017. 8. 13..
 */

class AlbumDetailPictureAdapter(var fragment: AlbumDetailFragment, var albumList: ArrayList<Picture>, var aa: Boolean = false, var bb:Boolean = false) : RecyclerView.Adapter<AlbumDetailPictureViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailPictureViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)



        return AlbumDetailPictureViewHolder(layoutInflater.inflate(R.layout.album_detail_picture_item, parent, false),fragment)
    }




    override fun onBindViewHolder(holder: AlbumDetailPictureViewHolder, position: Int) {

        val item = albumList[position]
        Glide.with(fragment.activity)
                .load(File(item.picturePath))
                .into(holder.picture)


        if (fragment.isInActionMode) {


            holder.checkbox.visibility = View.VISIBLE
            holder.checkbox.isChecked = false
            holder.layer.visibility = View.VISIBLE

            fragment.fragment_album_detail_toolbar.menu.clear()
            fragment.fragment_album_detail_toolbar.inflateMenu(R.menu.menu_detail_album_action_mode)
            fragment.fragment_album_detail_toolbar.title

            var deleteDrawable = fragment.fragment_album_detail_toolbar.menu.findItem(R.id.delete_menu)?.icon
            deleteDrawable?.setColorFilter(ContextCompat.getColor(fragment.context, R.color.text_secondary), PorterDuff.Mode.SRC_ATOP)


            var optionDrawable = fragment.fragment_album_detail_toolbar.menu.findItem(R.id.option_menu)?.icon
            optionDrawable?.setColorFilter(ContextCompat.getColor(fragment.context, R.color.text_secondary), PorterDuff.Mode.SRC_ATOP)



            (fragment.activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            fragment.fragment_album_detail_toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp)
            fragment.fragment_album_detail_toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(fragment.context, R.color.text_secondary), PorterDuff.Mode.SRC_ATOP)


        } else {
            holder.checkbox.visibility = View.GONE
            holder.checkbox.isChecked = false
            holder.layer.visibility = View.GONE

            fragment.fragment_album_detail_toolbar.menu.clear()
            fragment.fragment_album_detail_toolbar.inflateMenu(R.menu.menu_detail_album)
            fragment.fragment_album_detail_toolbar.title

            var pictureDrawable = fragment.fragment_album_detail_toolbar.menu.findItem(R.id.take_picture)?.icon
            pictureDrawable?.setColorFilter(ContextCompat.getColor(fragment.context, R.color.text_secondary), PorterDuff.Mode.SRC_ATOP)
            var galleryDrawable = fragment.fragment_album_detail_toolbar.menu.findItem(R.id.gallery)?.icon
            galleryDrawable?.setColorFilter(ContextCompat.getColor(fragment.context, R.color.text_secondary), PorterDuff.Mode.SRC_ATOP)
            var shareDrawable = fragment.fragment_album_detail_toolbar.menu.findItem(R.id.share)?.icon
            shareDrawable?.setColorFilter(ContextCompat.getColor(fragment.context, R.color.text_secondary), PorterDuff.Mode.SRC_ATOP)



            (fragment.activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            fragment.fragment_album_detail_toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp)
            fragment.fragment_album_detail_toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(fragment.context, R.color.text_secondary), PorterDuff.Mode.SRC_ATOP)

        }

        holder.layer.setOnClickListener{
//            holder.checkbox.isChecked = !holder.checkbox.isChecked


            if (holder.checkbox.isChecked) {
                holder.layer.background = null
                holder.checkbox.isChecked = false
            } else {

                holder.layer.setBackgroundColor(ContextCompat.getColor(fragment.context, R.color.transparent_layer)
                )
                holder.checkbox.isChecked = true
            }

//            fragment.prepareSelection(holder.checkbox, position)

        }

        // TODO: 클릭할때 상세 사진보기페이지로 넘어감
        holder.picture.setOnClickListener {
            var intent = Intent(fragment.activity, ImageViewActivity::class.java)
                intent.putExtra("cameraPath", item.picturePath)
                fragment.activity.startActivity(intent)

        }




        holder.picture.setOnLongClickListener{
            fragment.isInActionMode = true;


            fragment.mAlbumViewAdapter.notifyDataSetChanged()

            return@setOnLongClickListener true


        }



        holder.checkbox.setOnCheckedChangeListener { compoundButton, isChecked ->

            if (!isChecked) {
                holder.layer.background = null
                holder.checkbox.isChecked = false

            } else {
                holder.layer.setBackgroundColor(ContextCompat.getColor(fragment.context, R.color.transparent_layer)
                )
                holder.checkbox.isChecked = true

            }
//            fragment.prepareSelection(holder.checkbox, position)
        }



    }
    override fun getItemCount(): Int {
        return albumList.size
    }





}


