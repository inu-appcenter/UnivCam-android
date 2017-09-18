package com.inuappcenter.univcam_android.views.AlbumViews

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.database.RealmHelper
import com.inuappcenter.univcam_android.entites.Album
import com.inuappcenter.univcam_android.fragments.AlbumSelectFragment
import io.realm.Realm
import java.io.File
import java.util.*


/**
 * Created by ichaeeun on 2017. 7. 29..
 */

class AlbumViewSelectAdapter(var fragment: AlbumSelectFragment, var albumList: ArrayList<Album>) : RecyclerView.Adapter<AlbumSelectViewHolder>() {

    private lateinit var realm: Realm
    private lateinit var realmHelper: RealmHelper


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumSelectViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlbumSelectViewHolder(layoutInflater.inflate(R.layout.album_select_item, parent, false), fragment)
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: AlbumSelectViewHolder, position: Int) {
        Realm.init(fragment.context)
        realm = Realm.getDefaultInstance()

        realmHelper = RealmHelper(realm)
//        realmHelper.updateAlbumSorted()


        val item = albumList[position]
        holder.tv_title.setText(item.albumName)

        if (realmHelper.getLatestPicturePath(item.albumName) != null) {
            Glide.with(fragment.context)
                    .load(File(realmHelper.getLatestPicturePath(item.albumName)))
                    .into(holder.thumbnail)
        } else {
            holder.thumbnail.setImageResource(R.drawable.img_box_174_dp);
        }

        holder.tv_quantity.setText("${realmHelper.countPictures(item.albumName)}장의 사진")




    }




}


