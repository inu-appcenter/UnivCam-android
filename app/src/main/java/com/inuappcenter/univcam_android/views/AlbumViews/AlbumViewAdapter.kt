package com.inuappcenter.univcam_android.views.AlbumViews

import android.content.Context
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.entites.Album


/**
 * Created by ichaeeun on 2017. 7. 29..
 */

class AlbumViewAdapter(var context: Context) : RecyclerView.Adapter<AlbumViewHolder>() {

    // TODO : 예외처리- list없을 경우
    var mAlbumList: ArrayList<Album> = ArrayList()

    fun addAlbum(album: Album) {
        mAlbumList.add(0, album)
        notifyItemInserted(0)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlbumViewHolder(layoutInflater.inflate(R.layout.list_album, parent, false))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item = mAlbumList.get(position)
        Glide.with(context)
                .load(item.thumbnail)
                .into(holder.thumbnail)
        holder.tv_title.setText(item.title)
        holder.tv_quantity.setText("${item.quantity}장의 사진")
        holder.is_favorite.setChecked(item.isFavorite)
        holder.album_menu.setOnClickListener {
            var popupMenu = PopupMenu(context, holder.album_menu)
            popupMenu.inflate(R.menu.album_menu)
            popupMenu.setOnMenuItemClickListener{
                when(it.itemId) {
                    R.id.text_name -> {

                        true
                    }
                    R.id.text_name_reverse -> {

                        true
                    }
                    R.id.text_name -> {

                        true
                    }
                    R.id.time -> {

                        true
                    }
                    R.id.text_time_reverse -> {

                        true
                    }

                    else -> {
                        false
                    }
                }

            }
            popupMenu.show()
        }
    }

    override fun getItemCount(): Int {
        return mAlbumList.size
    }
}


