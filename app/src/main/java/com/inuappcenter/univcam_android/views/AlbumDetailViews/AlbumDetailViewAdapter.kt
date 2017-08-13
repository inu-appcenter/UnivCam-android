package com.inuappcenter.univcam_android.views.AlbumDetailViews

import android.app.Activity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.database.RealmHelper
import com.inuappcenter.univcam_android.entites.AlbumDetail
import com.inuappcenter.univcam_android.entites.Picture
import com.inuappcenter.univcam_android.fragments.AlbumDetailFragment
import io.realm.Realm
import kotlin.collections.ArrayList

/**
 * Created by ichaeeun on 2017. 8. 13..
 */

class AlbumDetailViewAdapter(var fragment: AlbumDetailFragment, var context: Activity, var dataList: ArrayList<AlbumDetail>) : RecyclerView.Adapter<AlbumDetailViewHolder>() {

    private lateinit var realm: Realm
    private lateinit var realmHelper: RealmHelper


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlbumDetailViewHolder(layoutInflater.inflate(R.layout.album_detail_item, parent, false))

    }

    override fun onBindViewHolder(holder: AlbumDetailViewHolder, position: Int) {
        val item = dataList.get(position)
        var pictureList: ArrayList<Picture> = item.pictureList
        holder.headerTitle.setText(item.headerTitle)
        //TODO: 이미지 가져오는 DB- 처음은 default
//        Glide.with(context)
//                .load(item.thumbnailUri)
//                .into(holder.thumbnail)
        holder.pictureRecyclerview.let{
            it.layoutManager = GridLayoutManager(context, 3)
            it.setNestedScrollingEnabled(false)  // TODO : nestedscroll
        }

        Realm.init(context)
        realm = Realm.getDefaultInstance()

        realmHelper = RealmHelper(realm)
        realmHelper.retrieveFromDB()
        var albumDetailPictureAdapter = AlbumDetailPictureAdapter(fragment, pictureList)
        holder.pictureRecyclerview.adapter = albumDetailPictureAdapter

    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}