package com.inuappcenter.univcam_android.views.AlbumViews

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.inuappcenter.univcam_android.R
import kotlinx.android.synthetic.main.list_album.view.*

/**
 * Created by ichaeeun on 2017. 7. 29..
 */

class AlbumViewHolder(view :View): RecyclerView.ViewHolder(view), CompoundButton.OnCheckedChangeListener {

    val tv_title: TextView = itemView.title
    val tv_quantity: TextView = itemView.quantity
    val thumbnail: ImageView = itemView.thumbnail
    val is_favorite: CheckBox = itemView.isFavorite
    val album_menu: ImageButton = itemView.album_menu
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


    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {

        //TODO : star버튼 누르면 디비에 저장
//        val position = layoutPosition
//        val post = arrayList.get(position)


        /*LikeTaskRequest likeTaskRequest = new LikeTaskRequest(isChecked,post.getId(), getUserUUID());
            LikeTask likeTask = new LikeTask();
            likeTask.execute(likeTaskRequest);*/
    }



}