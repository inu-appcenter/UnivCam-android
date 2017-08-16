package com.inuappcenter.univcam_android.views.AlbumViews

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.activities.AlbumDetailActivity
import com.inuappcenter.univcam_android.database.RealmHelper
import com.inuappcenter.univcam_android.entites.Album
import com.inuappcenter.univcam_android.entites.Picture
import com.inuappcenter.univcam_android.fragments.AlbumFragment
import io.realm.Realm
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by ichaeeun on 2017. 7. 29..
 */

class AlbumViewAdapter(var fragment: AlbumFragment, var context: Activity, var albumList: ArrayList<Album>) : RecyclerView.Adapter<AlbumViewHolder>() {

    private lateinit var realm: Realm
    private lateinit var realmHelper: RealmHelper

    fun addAlbum(album: Album) {
//        albumList.add(0, album)
        albumList.add(album)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlbumViewHolder(layoutInflater.inflate(R.layout.album_item, parent, false))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        Realm.init(context)
        realm = Realm.getDefaultInstance()

        realmHelper = RealmHelper(realm)
        realmHelper.retrieveFromDB()

        val item = albumList.get(position)
        //TODO: 이미지 가져오는 DB- 처음은 default
//        Glide.with(context)
//                .load(item.thumbnailUri)
//                .into(holder.thumbnail)
        holder.thumbnail.setImageResource(R.drawable.img_example)
        holder.tv_title.setText(item.albumName)
        holder.tv_quantity.setText("${item.quantity}장의 사진")
        holder.is_favorite.setChecked(item.favorite)
        holder.album_menu.setOnClickListener {
            var popupMenu = PopupMenu(context, holder.album_menu)
            popupMenu.inflate(R.menu.menu_album)
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

        holder.is_favorite.setOnCheckedChangeListener {
            compoundButton, isChecked ->
            realmHelper.updateFavoriteAlbum(item.albumName, isChecked)
        }

        // TODO : 카메라 사진 찍기
        holder.camera_button.setOnClickListener{
            takePicture(position, holder.tv_title.text.toString())
        }

        //TODO: DetailPage
        holder.select_layout.setOnClickListener {
            val intent = Intent(context, AlbumDetailActivity::class.java)
            intent.putExtra("albumName", holder.tv_title.text.toString())
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    private fun takePicture(position: Int, albumName: String) {
        val intent = Intent()
        val TAKE_CAMERA = 100

        var date = Date()
        val albumPath = File(context.getExternalFilesDir(null),albumName)
        val fileName = SimpleDateFormat("yyyyMMdd_HH_mm_ssSSS").format(date)
        val category = SimpleDateFormat("yyyy년 MM월 dd일").format(date)
        val yearMonthday = SimpleDateFormat("yyyyMMdd").format(date)
        val filePath = albumPath.toString() + File.separator + fileName + ".jpg"

        val file = File(filePath)
        val outputFileUri = Uri.fromFile(file)

        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
        context.startActivityForResult(intent, TAKE_CAMERA)
        var picture: Picture = Picture(file.toString(), date,yearMonthday, category)

        realmHelper.savePicture(albumName, picture)
    }

}


