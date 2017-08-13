package com.inuappcenter.univcam_android.views.AlbumViews

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.activities.AlbumDetailActivity
import com.inuappcenter.univcam_android.entites.Album
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by ichaeeun on 2017. 7. 29..
 */

class FavoriteViewAdapter(var context: Activity, var albumList: ArrayList<Album>) : RecyclerView.Adapter<FavoriteViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoriteViewHolder(layoutInflater.inflate(R.layout.album_item, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = albumList.get(position)
        //TODO: 이미지 가져오는 DB- 처음은 default
//        Glide.with(context)
//                .load(item.thumbnailUri)
//                .into(holder.thumbnail)
        holder.thumbnail.setImageResource(R.drawable.img_example)
        holder.tv_title.setText(item.albumName)
        holder.is_favorite.visibility= View.GONE
        holder.tv_quantity.setText("${item.quantity}장의 사진")
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



        // TODO : 카메라 사진 찍기
        holder.camera_button.setOnClickListener{
            takePicture(holder.tv_title.text.toString())
        }

        //TODO: DetailPage
        holder.select_layout.setOnClickListener {
            val intent = Intent(context, AlbumDetailActivity::class.java)
            intent.putExtra("albumName", holder.tv_title.toString())
            context.startActivity(intent)
        }


    }


    override fun getItemCount(): Int {
        return albumList.size
    }

    private fun takePicture(albumName: String) {
        val intent = Intent()
        val TAKE_CAMERA = 100

        val albumPath = File(context.getExternalFilesDir(null),albumName)
        val fileName = SimpleDateFormat("yyyyMMdd_HH_mm_ssSSS").format(Date())
        val filePath = albumPath.toString() + File.separator + fileName + ".jpg"

        val file = File(filePath)
        val outputFileUri = Uri.fromFile(file)

        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
        context.startActivityForResult(intent, TAKE_CAMERA)
    }

}


